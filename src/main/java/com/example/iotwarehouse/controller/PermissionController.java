package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.PermissionSearch;
import com.example.iotwarehouse.entity.Permission;
import com.example.iotwarehouse.mapper.PermissionMapper;
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
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询所有权限信息
     * 
     * @return Result类
     */
    @RequestMapping("/getAllPermissions")
    public Object getAllPermissions() {
        List<Permission> permissions = permissionMapper.selectList(null);
        return ResultUtil.isSuccess(permissions);
    }

    /**
     * 添加权限
     * 
     * @param permission Permission类
     * @return Result类
     */
    @PostMapping("/addPermission")
    public Object addPermission(@RequestBody Permission permission) {
        int i = permissionMapper.insert(permission);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新权限
     * 
     * @param permission Permission类
     * @return Result类
     */
    @PostMapping("/updatePermission")
    public Object updatePermission(@RequestBody Permission permission) {
        int i = permissionMapper.updateById(permission);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除权限
     * 
     * @param permId 权限ID
     * @return ResultUtil
     */
    @PostMapping("/delPermission")
    public Object delPermission(Integer permId) {
        int i = permissionMapper.deleteById(permId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param permissionSearch 查询类
     * @return ResultUtil
     */
    @RequestMapping("/getAllPermissionsByCon")
    public Object getAllPermissionsByCon(@RequestBody PermissionSearch permissionSearch) {
        // 分页对象
        Page<Permission> page = new Page<>(permissionSearch.getPageNo(), permissionSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        if (permissionSearch.getPermName() != null && !permissionSearch.getPermName().equals("")) {
            wrapper.like("perm_name", permissionSearch.getPermName());
        }
        if (permissionSearch.getModule() != null && !permissionSearch.getModule().equals("")) {
            wrapper.eq("module", permissionSearch.getModule());
        }

        List<Permission> permissions = permissionMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(permissions);
    }
}
