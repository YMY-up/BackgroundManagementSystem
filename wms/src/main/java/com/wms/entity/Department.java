package com.wms.entity;
import java.io.Serializable;
import lombok.Data;
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    // 主键
    private Integer id;
    // 用户姓名
    private String name;
    // 角色
    private String role;
    // 部门
    private String department;
    // 子id
    private Integer pid;


}