package com.qxn.factorypattern.factoryPattern.constant;

import com.qxn.factorypattern.factoryPattern.Db.ClickhouseOperate;
import com.qxn.factorypattern.factoryPattern.Db.HiveOperate;
import com.qxn.factorypattern.factoryPattern.IDbOperate;

/**
 * 数据操作常量
 */
public enum OperateEnum {

    CK_OPERATE("ck","clickhouse", new ClickhouseOperate()),
    HIVE_OPERATE("hive","hive数仓", new HiveOperate());

    private String operateCode;
    private String operateName;
    private IDbOperate operate;

    OperateEnum(String operateCode, String operateName, IDbOperate operate) {
        this.operateCode = operateCode;
        this.operateName = operateName;
        this.operate = operate;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public IDbOperate getOperate() {
        return operate;
    }

    public void setOperate(IDbOperate operate) {
        this.operate = operate;
    }

}