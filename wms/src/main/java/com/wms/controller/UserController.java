package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.entity.User;
import com.wms.utils.Md5;
import com.wms.service.MenuService;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    Md5 md5 = new Md5();
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
//   用于将 HTTP 的get 请求映射到特定处理程序的方法注解
//    @RequestMapping(method = RequestMethod.GET)缩写的组合注解
    public List<User> list(){
        return userService.list();
    }

    /**
     * 存储
     * @param user  用户
     * @return 返回登录结果
     */
    @PostMapping("/save")
//   用于将 HTTP 的post 请求映射到特定处理程序的方法注解
//    @RequestMapping(method = RequestMethod.POST)缩写的组合注解
    public Result save(@RequestBody User user){
        user.setPassword(md5.Md5(md5.Md5(user.getPassword())));
        return userService.save(user)?Result.suc():Result.fail();
    }

    /**
     * 根据用户ID进行删除
     * @param id 传入id
     * @return
     */
    //
    @GetMapping("/delete")
    public boolean delete(Integer id ){
        return userService.removeById(id);
    }

    /**
     *  修改
     * @param user 传入用户对象
     * @return 返回修改结果
     */
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        user.setPassword(md5.Md5(md5.Md5(user.getPassword())));
        return userService.updateById(user);
    }

    /**
     * 新增或修改(编辑功能)
     * @param user  传入user对象
     * @return  返回更新结果
     */
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        //根据id处理,传的User对象有Id,或者Id在表里层,就去更新，若这个id没有传、或者id不存在那么就去新增
        user.setPassword(md5.Md5(md5.Md5(user.getPassword())));
        return userService.saveOrUpdate(user);
    }

    /**
     * 更新用户信息
     * @param user  传入用户对象
     * @return  返回对应结果
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        user.setPassword(md5.Md5(md5.Md5(user.getPassword())));
        return userService.updateById(user)?Result.suc():Result.fail();
    }

    /**
     * 根据id进行删除对象
     * @param id  传入用户id
     * @return  返回对应结果
     */
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(id)?Result.suc():Result.fail();
    }

    /**
     * 登录
     * @param user  传入用户对象
     * @return  返回对应结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List list = userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                // 遍历User数据库账号
                .eq(User::getPassword,md5.Md5(md5.Md5(user.getPassword()))).list();
                // 遍历User数据库密码
                // 并存入到集合中
        if (list.size()>0){
            User user1 = (User)list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuRight,user1.getRoleId()).list();
            String key="user:"+ UUID.randomUUID();
            redisTemplate.opsForValue().set(key,user1,30, TimeUnit.MINUTES);
            HashMap res = new HashMap();
            res.put("user",user1);
            res.put("menu",menuList);
            return Result.suc(res);
        }
        // 如果集合中的对象数量<0，就证明账号不存在
        return Result.fail();
    }

    /**
     * 模糊查询
     * @param user 传入用户对象
     * @return
     */
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())){
            // 如果不是空，就模糊查询
            // 模糊查询,名字匹配
            lambdaQueryWrapper.like(User::getName,user.getName());
        }
        // 精准查询
//        lambdaQueryWrapper.eq(User::getName,user.getName());

        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    /**
     * 分页方法
     * @param query 分页功能对象
     * @return
     */
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name==="+(String)param.get("name"));
        Page<User> page = new Page<>();
        // 设置当前页码
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        // mybatis-plus的查询方法 创建查询对象
        // 防止SQL注入
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 进行模糊查询
        lambdaQueryWrapper.like(User::getName,name);
        //
        IPage result = userService.page(page,lambdaQueryWrapper);
        // 输出总数量
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }

    /**
     * 分页查询
     * @param query 分页对象
     * @return  返回结果
     */
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");
        String roleId = (String)param.get("roleId");
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            // 不为空
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }

    /**
     * 查找账号是否唯一的方法
     * @param no    传入的账号
     * @return 如果查询到的list集合中有数据(list > 0)那么就证明该账号存在
     */
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = userService.lambdaQuery().eq(User::getNo,no).list();
        return list.size()>0 ? Result.suc(list):Result.fail();
    }
}
