package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.PermissionSearch;
import com.example.iotwarehouse.entity.Permission;
import com.example.iotwarehouse.mapper.PermissionMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/permission")
@Tag(name = "权限管理", description = "权限信息的增删改查操作")
public class PermissionController {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询所有权限信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllPermissions")
    @Operation(summary = "获取所有权限", description = "查询系统中所有权限的信息")
    public ResultUtil getAllPermissions() {
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
    @Operation(summary = "添加权限", description = "创建新的权限")
    public ResultUtil addPermission(@Parameter(description = "权限信息") @RequestBody Permission permission) {
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
    @Operation(summary = "更新权限", description = "根据ID更新权限信息")
    public ResultUtil updatePermission(@Parameter(description = "权限信息") @RequestBody Permission permission) {
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
    @Operation(summary = "删除权限", description = "根据ID删除权限")
    public ResultUtil delPermission(@Parameter(description = "权限ID") @RequestParam Integer permId) {
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
    @PostMapping("/getAllPermissionsByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询权限信息")
    public ResultUtil getAllPermissionsByCon(
            @Parameter(description = "查询条件") @RequestBody PermissionSearch permissionSearch) {
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
