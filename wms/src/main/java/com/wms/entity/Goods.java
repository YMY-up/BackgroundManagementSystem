package com.wms.entity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private int id;
    // 货名
    private String name;
    // 仓库
    private Integer storage;
    // 分类
    private Integer goodstype;
    // 数量
    private Integer count;
    // 备注
    private String remark;


}
