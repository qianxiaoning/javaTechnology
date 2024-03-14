package com.qxn.factorypattern;

import com.qxn.factorypattern.factoryPattern.IDbOperate;
import com.qxn.factorypattern.factoryPattern.OperateFactory;
import com.qxn.factorypattern.factoryPattern.constant.OperateEnum;
import com.qxn.factorypattern.factoryPattern.vo.TableDataOperateVo;
import org.junit.jupiter.api.Test;


public class SimpleTest {

    @Test
    public void getDbOperateByHardcode() throws Exception {
        TableDataOperateVo vo = new TableDataOperateVo();
        vo.setTableName("表A");

        IDbOperate hive = OperateFactory.getDbOperateByHardcode("hive");
        hive.updateOneData(vo);
    }

    @Test
    public void getDbOperateByEnum() throws Exception {
        TableDataOperateVo vo = new TableDataOperateVo();
        vo.setTableName("表B");

        IDbOperate hive = OperateFactory.getDbOperateByEnum("ck");
        hive.updateOneData(vo);
    }

}