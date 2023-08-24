package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;


public interface StorageService extends IService<Storage> {
    IPage pageCC(IPage<Storage> page, Wrapper wrapper);

}
