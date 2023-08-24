package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService MenuService;

    /**
     * 新增
     * @param menu  菜单对象
     * @return res
     */
    @PostMapping("/save")
    public Result save(@RequestBody Menu menu){
        return MenuService.save(menu)?Result.suc():Result.fail();
    }

    /**
     * 更新
     * @param menu 菜单对象
     * @return res
     */
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu){
        return MenuService.updateById(menu)?Result.suc():Result.fail();
    }

    /**
     * 删除
     * @param id 菜单ID
     * @return res
     */
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return MenuService.removeById(id)?Result.suc():Result.fail();
    }

    /**
     * 分页查询
     * @param query 分页对象
     * @return res
     */
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Menu> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Menu::getMenuName,name);
        }

        IPage result = MenuService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    /***
     * 查询所有的menu
     * @param roleId    角色id
     * @return list集合
     */
    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List list = MenuService.lambdaQuery().like(Menu::getMenuRight,roleId).list();
        return  Result.suc(list);
    }

}
