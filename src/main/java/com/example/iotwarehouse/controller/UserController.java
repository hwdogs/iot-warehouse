package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.UserLogin;
import com.example.iotwarehouse.entity.User;
import com.example.iotwarehouse.common.UserSearch;
import com.example.iotwarehouse.mapper.UserMapper;
import com.example.iotwarehouse.service.IUserService;
import com.example.iotwarehouse.util.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询所有未被删除的用户信息
     * 
     * @return Result类
     */
    @RequestMapping("/getAllUsers")
    public Object getAllUser() {
        List<User> users = userMapper.selectList(null);
        return ResultUtil.isSuccess(users);
    }

    /**
     * 添加用户
     * 
     * @param user User类
     * @return Result类
     */
    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user) {
        int i = userMapper.insert(user);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新用户
     * 
     * @param user User类
     * @return Result类
     */
    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user) {
        int i = userMapper.updateById(user);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除用户
     * 
     * @param userId 根据的ID
     * @return ResultUtil
     */
    @PostMapping("/delUser")
    public Object delUser(Integer userId) {
        int i = userMapper.deleteById(userId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param userSearch 查询类
     * @return ResultUtil
     */
    @RequestMapping("/getAllUserByCon")
    public Object getAllUserByCon(@RequestBody UserSearch userSearch) {
        // 分页对象
        Page<User> page = new Page<>(userSearch.getPageNo(), userSearch.getPageSize());
        // 条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (userSearch.getUsername() != null && !userSearch.getUsername().equals("")) {
            wrapper.like("username", userSearch.getUsername());
        }
        if (userSearch.getRole() != null) {
            wrapper.eq("role", userSearch.getRole());
        }
        List<User> users = userMapper.selectList(page, wrapper);// 多条件分页后的数据
        System.out.println("总记录数：" + page.getTotal());
        return ResultUtil.isSuccess(users);
    }

    @Resource
    IUserService iUserService;

    /**
     * 登录接口
     * 
     * @param userLogin UserLogin类
     * @return ResultUtil
     */
    @PostMapping("/login")
    public Object login(@RequestBody UserLogin userLogin) {
        return iUserService.loginLogic(userLogin.getUsername(), userLogin.getPassword());
    }

    /**
     * 注册接口
     * 
     * @param user user类
     * @return ResultUtil
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        return iUserService.registerLogic(user);
    }

    /**
     * 根据邮箱更新用户信息
     * 
     * @param email    邮箱
     * @param password 面膜
     * @return ResultUtil
     */
    @PostMapping("/updateByEmail")
    public Object updatePasswordByEmail(@RequestParam("email") String email,
            @RequestParam("password") String password) {
        return iUserService.updatePasswordByEmail(email, password);
    }
}
