package com.qxn.designpattern.factoryPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <简述> 函数工厂
 * <详细描述> FunctionFactory
 */
@Component
public class FunctionFactory {

    /**
     * @Field funcMap : 函数map
     */
    private Map<String, Function> funcMap;

    /**
     * @Field funcList : 所有继承Function接口的类
     */
    @Autowired
    private List<Function> funcList;

    /**
     * <简述> 项目启动时执行该方法
     * <详细描述> init
     * @author qianxiaoning
     * @return void
     */
    @PostConstruct
    public void init() {
        // Collectors.toMap，3个参数，(键,值,新老键重复时的取键方式)
        this.funcMap = funcList.stream().collect(Collectors.toMap(e -> e.getCode(), e -> e, (e1, e2) -> e1));
    }

    public Function get(String code){
        Function func = this.funcMap.get(code);
        if (func == null) {
            // TODO throw 找不到函数异常
        }
        return func;
    }
}
