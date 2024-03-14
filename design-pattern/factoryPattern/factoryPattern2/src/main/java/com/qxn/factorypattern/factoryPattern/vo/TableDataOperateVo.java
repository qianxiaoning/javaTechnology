package com.qxn.factorypattern.factoryPattern.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <简述> 表数据操作vo
 * <详细描述> TableDataOperateVo
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
@Data
public class TableDataOperateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    private String tableName;

}
