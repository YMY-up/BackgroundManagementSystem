package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goodstype;
import com.wms.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {
    @Autowired
    private GoodstypeService goodstypeService;
    /**
     * 新增
     * @param goodstype 产品类型对象
     * @return 返回对应结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype){
        return goodstypeService.save(goodstype)?Result.suc():Result.fail();
    }

    /**
     * 更新/修改
     * @param goodstype 产品类型对象
     * @return  返回对应结果
     */
    @PostMapping("/update")
    public Result update(@RequestBody Goodstype goodstype){
        return goodstypeService.updateById(goodstype)?Result.suc():Result.fail();
    }

    /**
     *  根据id删除
     * @param id 产品类型id
     * @return res
     */
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return goodstypeService.removeById(id)?Result.suc():Result.fail();
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

        Page<Goodstype> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodstype::getName,name);
        }

        IPage result = goodstypeService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    /**
     * 产品类型集合
     * @return  res
     */
    @GetMapping("/list")
    public Result list(){
        List list = goodstypeService.list();
        return Result.suc(list);
    }
}
