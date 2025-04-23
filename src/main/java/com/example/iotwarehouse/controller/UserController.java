package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.request.userRequest.*;
import com.example.iotwarehouse.entity.User;
import com.example.iotwarehouse.mapper.UserMapper;
import com.example.iotwarehouse.service.IUserService;
import com.example.iotwarehouse.util.MD5Util;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户管理", description = "用户信息的增删改查操作")
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询所有未被删除的用户信息
     *
     * @return Result类
     */
    @Operation(summary = "获取所有用户", description = "查询系统中所有用户的信息")
    @GetMapping("/getAllUsers")
    public ResultUtil getAllUser() {
        List<User> users = userMapper.selectList(null);
        return ResultUtil.isSuccess(users);
    }

    /**
     * 添加用户
     *
     * @param request AddUserRequest类
     * @return Result类
     */
    @PostMapping("/addUser")
    @Operation(summary = "添加用户", description = "创建新的用户")
    public ResultUtil addUser(@RequestBody AddUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(MD5Util.string2MD5(request.getPasswordHash()))
                .passwordConfirm(MD5Util.string2MD5(request.getPasswordConfirm()))
                .email(request.getEmail())
                .biometricToken(request.getBiometricToken())
                .role(request.getRole()).build();
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
     * @param request UpdateUserRequest类
     * @return Result类
     */
    @PostMapping("/updateUser")
    @Operation(summary = "更新用户", description = "根据ID更新用户信息")
    public ResultUtil updateUser(@RequestBody UpdateUserRequest request) {
        User user = User.builder().userId(request.getUserId())
                .username(request.getUsername())
                .passwordHash(MD5Util.string2MD5(request.getPasswordHash()))
                .passwordConfirm(MD5Util.string2MD5(request.getPasswordConfirm()))
                .email(request.getEmail())
                .biometricToken(request.getBiometricToken())
                .role(request.getRole()).build();
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
    @Operation(summary = "删除用户", description = "根据ID删除用户")
    public ResultUtil delUser(Integer userId) {
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
     * @param request UserSearchRequest查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllUserByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询用户信息")
    public ResultUtil getAllUserByCon(@RequestBody UserSearchRequest request) {
        // 分页对象
        Page<User> page = new Page<>(request.getPageNo(), request.getPageSize());
        // 条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (request.getUsername() != null && !request.getUsername().equals("")) {
            wrapper.like("username", request.getUsername());
        }
        if (request.getRole() != null) {
            wrapper.eq("role", request.getRole());
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
    @Operation(summary = "登录接口", description = "输入用户名和密码登录")
    public ResultUtil login(@RequestBody UserLoginRequest userLogin) {
        return iUserService.loginLogic(userLogin.getUsername(), userLogin.getPassword());
    }

    /**
     * 注册接口
     *
     * @param request UserRegisterRequest类
     * @return ResultUtil
     */
    @Operation(summary = "注册接口", description = "输入用户名,密码,确认密码,邮箱,同意用户协议后注册")
    @PostMapping("/register")
    public ResultUtil register(@RequestBody UserRegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(request.getPasswordHash())
                .passwordConfirm(request.getPasswordConfirm())
                .email(request.getEmail())
                .biometricToken(request.getBiometricToken())
                .role(request.getRole()).build();
        return iUserService.registerLogic(user);
    }

    /**
     * 根据邮箱更新用户信息
     *
     * @param email    邮箱
     * @param password 面膜
     * @return ResultUtil
     */
    @Operation(summary = "通过邮箱更新用户密码", description = "通过邮箱更新用户密码")
    @PostMapping("/updateByEmail")
    public ResultUtil updatePasswordByEmail(@RequestParam("email") String email,
                                            @RequestParam("password") String password) {
        return iUserService.updatePasswordByEmail(email, password);
    }
}
