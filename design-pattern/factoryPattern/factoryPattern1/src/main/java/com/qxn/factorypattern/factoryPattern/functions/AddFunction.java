package com.qxn.factorypattern.factoryPattern.Db;

import com.qxn.factorypattern.factoryPattern.Function;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <简述> 加函数
 * <详细描述> AddFunction
 */
@Component
public class AddFunction implements Function {

    /**
     * @Field functionCode : 函数编码
     */
    private String functionCode = "add";

    /**
     * @Field functionCode : 函数名称
     */
    private String functionName = "加法";

    /**
     * @Field param1 : 被加数
     */
    private String param1 = "augend";

    /**
     * @Field param2 : 加数
     */
    private String param2 = "addend";

    @Override
    public String getCode() {
        return functionCode;
    }

    @Override
    public BigDecimal calculate(Map<String, Object> params) {
        // 计算逻辑
        Object o = params.get(param1);
        BigDecimal augend = new BigDecimal(o.toString());
        Object o1 = params.get(param2);
        BigDecimal addend = new BigDecimal(o1.toString());
        BigDecimal r = augend.add(addend);
        return r;
    }

}
