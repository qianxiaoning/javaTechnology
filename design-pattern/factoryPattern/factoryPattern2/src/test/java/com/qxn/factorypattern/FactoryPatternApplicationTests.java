package com.qxn.factorypattern;

import com.qxn.factorypattern.factoryPattern.IDbOperate;
import com.qxn.factorypattern.factoryPattern.OperateFactory;
import com.qxn.factorypattern.factoryPattern.vo.TableDataOperateVo;
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
class FactoryPatternApplicationTests {

    @Autowired
    private OperateFactory operateFactory;

    @Test
    void contextLoads() {
    }

    @Test
    public void getOperateBySpring() {
        TableDataOperateVo vo = new TableDataOperateVo();
        vo.setTableName("表A");

        IDbOperate ck = operateFactory.getOperateBySpring("ck");
        ck.deleteOneData(null);
        ck.addOneData(vo);
        IDbOperate hive = operateFactory.getOperateBySpring("hive");
        hive.updateOneData(vo);
    }


}
