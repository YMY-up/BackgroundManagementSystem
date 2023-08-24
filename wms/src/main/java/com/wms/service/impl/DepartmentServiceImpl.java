package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.wms.entity.Department;
import com.wms.mapper.DepartmentMapper;
import com.wms.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public IPage pageCC(IPage<Department> page, Wrapper wrapper) {
        return departmentMapper.pageCC(page,wrapper);
    }
}