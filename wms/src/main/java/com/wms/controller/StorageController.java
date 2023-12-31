package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Storage;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {
    // 注入
    @Autowired
    private StorageService storageService;

    /**
     * 新增
     * @param storage 仓库对象
     * @return  res
     */
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage){
        return storageService.save(storage)?Result.suc():Result.fail();
    }

    /**
     *  根据id删除
     * @param id 仓库id
     * @return res
     */
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return storageService.removeById(id)?Result.suc():Result.fail();
    }

    /**
     * 修改
     * @param storage  仓库对象
     * @return  res
     */
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage){
        return storageService.updateById(storage)?Result.suc():Result.fail();
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
        // 记录页码
        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }
        IPage result = storageService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }

    /**
     * 仓库集合
     * @return res
     */
    @GetMapping("/list")
    public Result list(){
        List list = storageService.list();
        return Result.suc(list);
    }

}
