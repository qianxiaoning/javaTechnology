package com.qxn.factorypattern.factoryPattern;

import com.qxn.factorypattern.factoryPattern.Db.ClickhouseOperate;
import com.qxn.factorypattern.factoryPattern.Db.HiveOperate;
import com.qxn.factorypattern.factoryPattern.constant.OperateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <简述> 数据库操作工厂
 * <详细描述> OperateFactory
 */
@Component
public class OperateFactory {

    /**
     * @Field operateMap : 函数map
     */
    private Map<String, IDbOperate> operateMap;

    /**
     * @Field operateList : 所有继承Operate接口的类
     */
    @Autowired
    private List<IDbOperate> operateList;

    /**
     * <简述> 通过spring获取工厂对象-项目启动时执行该方法
     * <详细描述> init
     * @author qianxiaoning
     */
    @PostConstruct
    public void init() {
        // Collectors.toMap，3个参数，(键，值，新老键重复时的取键方式)
        this.operateMap = operateList.stream().collect(Collectors.toMap(e -> e.getCode(), e -> e, (e1, e2) -> e1));
    }

    /**
     * <简述> 通过spring获取工厂对象
     * <详细描述> getOperateBySpring
     * @author qianxiaoning
     * @param code
     * @return IDbOperate
     */
    public IDbOperate getOperateBySpring(String code){
        IDbOperate operate = this.operateMap.get(code);
        if (operate == null) {
            // TODO throw 找不到异常
            System.out.println("找不到异常");
        }
        return operate;
    }

    /**
     * <简述> 通过硬编码获取工厂对象
     * <详细描述> getDbOperateByText
     * @author qianxiaoning
     * @param code
     * @return IDbOperate
     */
    public static IDbOperate getDbOperateByHardcode(String code) {
        IDbOperate operater = null;
        if ("ck".equalsIgnoreCase(code)) {
            operater = new ClickhouseOperate();
        } else if ("hive".equalsIgnoreCase(code)) {
            operater = new HiveOperate();
        }
        return operater;
    }

    /**
     * <简述> 通过枚举获取工厂对象
     * <详细描述> getDbOperateByText
     * @author qianxiaoning
     * @param code
     * @return IDbOperate
     */
    public static IDbOperate getDbOperateByEnum(String code) {
        IDbOperate operateObj = null;
        for (OperateEnum operate : OperateEnum.values()) {
            String operateCode = operate.getOperateCode();
            if (operateCode.equalsIgnoreCase(code)) {
                operateObj = operate.getOperate();
            }
        }
        return operateObj;
    }
}
