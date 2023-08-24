package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Department;

public interface DepartmentService extends IService<Department> {
    IPage pageCC(IPage<Department> page, Wrapper wrapper);
}

