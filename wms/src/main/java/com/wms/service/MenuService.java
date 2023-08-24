package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MenuService extends IService<Menu> {

    IPage pageCC(IPage<Menu> page, Wrapper wrapper);
}
