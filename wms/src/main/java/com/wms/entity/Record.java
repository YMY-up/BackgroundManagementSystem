package com.wms.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    // 主键
    private int id;
    // 货品id
    private Integer goods;
    // 补货人id
    private Integer userid;
    // 操作人id
    private Integer adminId;
    // 数量
    private Integer count;

    // 操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createtime;
    // 备注
    private String remark;
    @TableField(exist = false)
    private String action;
}
