package com.wms.entity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;
    // 主键
    private int id;
    //仓库名
    private String name;
    // 备注
    private String remark;


}
