### factoryPattern 工厂模式
---
1.定义IDbOperate数据源接口，定义数据源公共方法： getCode, addOneData, updateOneData, ...

2.实现CommonOperate公共数据源类，继承IDbOperate接口，做公共实现

3.实现如ClickhouseOperate ck数据源类，继承CommonOperate父类，做clickhouse的方法具体实现

4.**OperateFactory工厂类**，分别用spring, 硬编码, 枚举，3种方式实现工厂的获取子类方法

5.FactoryPatternApplicationTests使用：  注入operateFactory工厂实例，operateFactory.getOperateBySpring("ck");获取子类

6.SimpleTest使用：  注入工厂对象，OperateFactory.getDbOperateByHardcode("hive"); 
OperateFactory.getDbOperateByEnum("ck");工厂对象根据code得到对应的函数子类
