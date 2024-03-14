package com.qxn.factorypattern.factoryPattern;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <简述> 函数接口
 * <详细描述> Function
 */
public interface Function {

    /**
     * <简述> 返回函数编码
     * <详细描述> getCode
     * @author qianxiaoning
     * @return String
     */
    String getCode();

    /**
     * <简述> 执行函数计算
     * <详细描述> calculate
     * @author qianxiaoning
     * @param params 函数参数
     * @return BigDecimal
     */
    BigDecimal calculate(Map<String, Object> params);

}
