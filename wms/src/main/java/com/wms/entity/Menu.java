package com.wms.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private Integer id;

    // 菜单编码
    private String menuCode;
    // 菜单名称
    private String menuName;
    // 菜单级别
    private String menuLevel;
    // 点击触发的函数
    private String menuClick;
    // 权限 0超级管理员，1表示管理员，2表示普通用户
    private String menuRight;
    // 路由
    private String menuComponent;
    // 图标
    private String menuIcon;


}
