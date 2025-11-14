### design pattern
#### java设计模式
---
- factoryPattern 工厂模式
---
待添加


、、、
需求：
java springboot
入参lotid,eqpid,durableid，到时候其中一个或几个id会有值
要根据这些id去不同的表里查询LotId, EqpId,FoupId,FosbId,ReticleId,ReticlePodId,ProductId,MainflowId,StepId,StepNo,RecipeId,Content这些值，最终放入一个dto
入参的id后面可能会扩展，不同的id对应不同的表，要查询的内容可能会扩展，
用策略模式 + 工厂模式实现，不用steam流，能否在策略类中定义不同id查询dto中哪几个值

答案：
策略模式 + 工厂模式
策略模式 = 把一组可能会变化的算法（逻辑）封装成独立对象，并在运行时自由替换。

通俗理解：
如果某段逻辑有多种不同实现方案（策略），根据不同情况选择不同的实现 → 用策略模式。

例如：
不同支付方式（支付宝、微信、银行卡）
不同排序算法（快排、归排）
不同价格计算方式（VIP价、满减价）
、、、
下面我给你一个 **完全符合你需求**、并且 **可扩展性最强** 的方案：

* ✔ **使用策略模式 + 工厂模式**
* ✔ **不用 Stream**
* ✔ **每个策略类中可独立定义：根据该 ID 需要查询哪些字段**
* ✔ **最终结果汇总到一个 DTO**
* ✔ **未来 ID 类型随便加、字段随便扩展，只新增类即可**

下面直接给你完整的工程级写法。

---

# ✅ 一、你的问题核心：

> **能否在策略类中定义“不同 ID 查询 DTO 中哪些字段”**
> 答：**能，而且这是策略模式的核心优势之一！**

每个策略类可以：

* 自己负责去不同的表查
* 自己负责写入 DTO 的哪些字段

---

# ✅ 二、整体设计结构

```
QueryRequest   ——入参（可能有多个 id）
QueryResultDTO ——最终汇总输出

QueryStrategy ——策略接口
  ├── LotIdQueryStrategy
  ├── EqpIdQueryStrategy
  ├── DurableIdQueryStrategy
  └── 未来继续扩展...
  
QueryStrategyFactory ——根据 request 决定使用哪些策略

QueryService ——执行所有策略并把结果 merge 到 DTO
```

---

# ✅ 三、代码实现（可直接用）

## 1）入参对象

```java
public class QueryRequest {
    private String lotId;
    private String eqpId;
    private String durableId;
    // 未来可以自由扩展，如 productId、fosbId...
}
```

---

## 2）输出 DTO（所有字段都在此）

```java
public class QueryResultDTO {
    private String lotId;
    private String eqpId;
    private String foupId;
    private String fosbId;
    private String reticleId;
    private String reticlePodId;
    private String productId;
    private String mainflowId;
    private String stepId;
    private String stepNo;
    private String recipeId;
    private String content;
}
```

---

## 3）策略接口（关键点：提供 match 和 apply）

```java
public interface QueryStrategy {

    /** 是否适用于该请求 */
    boolean match(QueryRequest req);

    /**
     * 执行查询，把查询的值写到 dto 中
     * 不同策略写入 dto 的字段不同
     */
    void apply(QueryRequest req, QueryResultDTO dto);
}
```

---

## 4）LotId 策略示例（可定义本策略负责写入哪些字段）

```java
@Component
public class LotIdQueryStrategy implements QueryStrategy {

    @Autowired
    private LotMapper lotMapper;

    @Override
    public boolean match(QueryRequest req) {
        return req.getLotId() != null;
    }

    @Override
    public void apply(QueryRequest req, QueryResultDTO dto) {
        LotEntity lot = lotMapper.selectById(req.getLotId());
        if (lot != null) {
            dto.setLotId(lot.getLotId());
            dto.setProductId(lot.getProductId());
            dto.setMainflowId(lot.getMainflowId());
            dto.setStepId(lot.getStepId());
            // 该策略负责 DTO 的哪些字段，由你自己决定
        }
    }
}
```

---

## 5）EqpId 策略示例

```java
@Component
public class EqpIdQueryStrategy implements QueryStrategy {

    @Autowired
    private EqpMapper eqpMapper;

    @Override
    public boolean match(QueryRequest req) {
        return req.getEqpId() != null;
    }

    @Override
    public void apply(QueryRequest req, QueryResultDTO dto) {
        EqpEntity eqp = eqpMapper.selectById(req.getEqpId());
        if (eqp != null) {
            dto.setEqpId(eqp.getEqpId());
            dto.setRecipeId(eqp.getRecipeId());
            // 写入 DTO 的哪些字段完全由你定义
        }
    }
}
```

---

## 6）DurableId 策略示例

```java
@Component
public class DurableIdQueryStrategy implements QueryStrategy {

    @Autowired
    private DurableMapper durableMapper;

    @Override
    public boolean match(QueryRequest req) {
        return req.getDurableId() != null;
    }

    @Override
    public void apply(QueryRequest req, QueryResultDTO dto) {
        DurableEntity durable = durableMapper.selectById(req.getDurableId());
        if (durable != null) {
            dto.setFoupId(durable.getFoupId());
            dto.setFosbId(durable.getFosbId());
            dto.setContent(durable.getContent());
        }
    }
}
```

---

# ✅ 四、策略工厂（不用 Stream）

```java
@Component
public class QueryStrategyFactory {

    @Autowired
    private List<QueryStrategy> strategyList;

    /** 返回所有匹配到的策略（允许多个） */
    public List<QueryStrategy> getStrategies(QueryRequest req) {
        List<QueryStrategy> result = new ArrayList<>();
        for (QueryStrategy s : strategyList) {
            if (s.match(req)) {
                result.add(s);
            }
        }
        return result;
    }
}
```

---

# ✅ 五、业务 Service（执行所有策略并合并结果）

```java
@Service
public class QueryService {

    @Autowired
    private QueryStrategyFactory factory;

    public QueryResultDTO query(QueryRequest req) {

        QueryResultDTO dto = new QueryResultDTO();

        List<QueryStrategy> strategies = factory.getStrategies(req);

        for (QueryStrategy strategy : strategies) {
            strategy.apply(req, dto);
        }

        return dto;
    }
}
```

---

# 🔥 六、为什么这个设计非常适合你的场景？

## ✔ 1）不同 ID 查询不同表

每个策略类只负责自己的表。

## ✔ 2）不同 ID 写入 DTO 不同字段

在 `apply()` 内自由决定写哪个字段。

## ✔ 3）未来扩展任意 ID／任意字段

新增逻辑 **只需新增一个策略类**，不用修改现有代码。

## ✔ 4）同一个请求可同时匹配多个 ID

例如既传 lotId 又传 eqpId，会执行两个策略，DTO 合并结果。

## ✔ 5）不用 Stream（完全满足你要求）

---

# 🔥 七、如果你愿意，我还能帮你

如果你提供：

* **Lot 表字段**
* **Eqp 表字段**
* **Durable 表字段**
* **未来可能扩展的表字段**

我帮你自动生成：

* 所有 Strategy 类
* Factory
* Service
* DTO
* Mapper 接口
* MyBatis-Plus 查询模板

你只要复制即可运行。

---

如果需要继续扩展和落地代码，告诉我即可。
