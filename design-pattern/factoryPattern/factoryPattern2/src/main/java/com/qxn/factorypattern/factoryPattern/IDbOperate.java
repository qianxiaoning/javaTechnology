package com.qxn.factorypattern.factoryPattern;

import com.qxn.factorypattern.factoryPattern.vo.TableDataOperateVo;


/**
 * <简述> 数据库操作
 * <详细描述> IDbOperate
 */
public interface IDbOperate {

    /**
     * <简述> 返回函数编码
     * <详细描述> getCode
     * @author qianxiaoning
     * @return String
     */
    String getCode();

    /**
     * <简述> 新增一条数据
     * <详细描述> addOneData
     * @author qianxiaoning
     * @param vo
     */
    void addOneData(TableDataOperateVo vo);

    /**
     * <简述> 修改一条数据
     * <详细描述> updateOneData
     * @author qianxiaoning
     * @param vo
     */
    void updateOneData(TableDataOperateVo vo);

    /**
     * <简述> 删除一条数据
     * <详细描述> deleteOneData
     * @author qianxiaoning
     * @param vo
     */
    void deleteOneData(TableDataOperateVo vo);

}
