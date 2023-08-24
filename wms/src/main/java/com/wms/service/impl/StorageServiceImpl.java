package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Storage;
import com.wms.mapper.StorageMapper;
import com.wms.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    @Override
    public IPage pageCC(IPage<Storage> page, Wrapper wrapper) {
        return storageMapper.pageCC(page,wrapper);    }
}
