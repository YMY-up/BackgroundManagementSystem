package com.wms.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.wms.entity.Department;
import com.wms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/tree")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


//    @GetMapping("/depart")
//    public List<Department> selectAll(){
//        return departmentService.list();
//    }

@GetMapping
public List<Tree<Integer>> getAll() {
    List<Department> deptList = departmentService.list();
    TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
    treeNodeConfig.setIdKey("id");
    treeNodeConfig.setParentIdKey("parentId");
    // 设置层级  也就是有几级子目录这种关系
    treeNodeConfig.setDeep(4);
    List<Tree<Integer>> build = TreeUtil.build(deptList, 0, treeNodeConfig, (treeNode, tree) -> {
        tree.setId(treeNode.getId()); //id
        tree.setParentId(treeNode.getPid()); //父id
        //TODO 返回的时候字段
        tree.putExtra("id",treeNode.getId());
        tree.putExtra("parentId",treeNode.getPid());
        tree.putExtra("role",treeNode.getRole());
        tree.putExtra("department",treeNode.getDepartment());
        tree.putExtra("name",treeNode.getName());
    });
    return build;
}

}
