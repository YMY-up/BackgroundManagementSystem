package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordRes extends  Record{

    // 补货人姓名
    private String username;
    // 操作人姓名
    private String adminname;
    // 产品名称
    private String goodsname;
    // 仓库名称
    private String storagename;
    // 产品类型名称
    private String goodstypename;
}
