package com.qxn.factorypattern.factoryPattern.Db;

import com.qxn.factorypattern.factoryPattern.vo.TableDataOperateVo;
import org.springframework.stereotype.Component;

/**
 * <简述> hive数据库操作子类
 * <详细描述> HiveOperate
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
@Component
public class HiveOperate extends CommonOperate {

    /**
     * @Field operateCode : 数据库编码
     */
    private String operateCode = "hive";

    /**
     * @Field operateName : 数据库名称
     */
    private String operateName = "hive数仓";

    @Override
    public String getCode() {
        return operateCode;
    }

    @Override
    public void addOneData(TableDataOperateVo vo) {
        String tableName = vo.getTableName();
        System.out.println("hive添加表: " + tableName);
    }

    @Override
    public void updateOneData(TableDataOperateVo vo) {
        super.updateOneData(null);
        String tableName = vo.getTableName();
        System.out.println("hive更新表: " + tableName);
    }

}