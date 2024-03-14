package com.qxn.factorypattern.factoryPattern.Db;

import com.qxn.factorypattern.factoryPattern.IDbOperate;
import com.qxn.factorypattern.factoryPattern.vo.TableDataOperateVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <简述> 公共数据库操作父类
 * <详细描述> DbOperateCommonServiceImpl
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
@Component
public class CommonOperate implements IDbOperate {

    /**
     * @Field operateCode : 数据库编码
     */
    private String operateCode = "common";

    /**
     * @Field operateName : 数据库名称
     */
    private String operateName = "公共类型";

    @Override
    public String getCode() {
        return operateCode;
    }

    @Override
    public void addOneData(TableDataOperateVo vo) {
        // 以后可以合并公共实现
    }

    @Override
    public void updateOneData(TableDataOperateVo vo) {
        // 以后可以合并公共实现
        System.out.println("公共更新方法");
    }

    @Override
    public void deleteOneData(TableDataOperateVo vo) {
        // 以后可以合并公共实现
        System.out.println("公共删除方法");
    }
}
