package com.wms.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    // 主键
    private int id;
    // 账号
    private String no;
    // 名字
    private String name;
    // 密码
    private String password;
    // 年龄
    private Integer age;
    // 性别
    private Integer sex;
    // 电话
    private String phone;
    // ⻆⾊ 0超级管理员，1管理员，2普通账号
    private Integer roleId;
    // 是否有效，Y有效，其他⽆效
    private String isvalid;


}
