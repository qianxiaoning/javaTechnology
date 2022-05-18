package com.qxn.designpattern;

import com.qxn.designpattern.factoryPattern.Function;
import com.qxn.designpattern.factoryPattern.FunctionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class DesignPatternApplicationTests {

    @Autowired
    private FunctionFactory functionFactory;

    @Test
    void contextLoads() {
    }

    @Test
    public void factoryPatternTest() {
        Function func = functionFactory.get("add");
        Map<String, Object> map = new HashMap<>();
        map.put("augend", 1);
        map.put("addend", 2);
        BigDecimal r = func.calculate(map);
        System.out.println(r);
    }

}
