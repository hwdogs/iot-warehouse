package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.WarehouseSearch;
import com.example.iotwarehouse.entity.Warehouse;
import com.example.iotwarehouse.mapper.WarehouseMapper;
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
@RequestMapping("/warehouse")
public class WarehouseController {

    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 查询所有仓库信息
     * 
     * @return Result类
     */
    @RequestMapping("/getAllWarehouses")
    public Object getAllWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectList(null);
        return ResultUtil.isSuccess(warehouses);
    }

    /**
     * 添加仓库
     * 
     * @param warehouse Warehouse类
     * @return Result类
     * 
     *         注意: location字段应使用"纬度 经度"格式字符串，例如: "31.2304 121.4737"
     */
    @PostMapping("/addWarehouse")
    public Object addWarehouse(@RequestBody Warehouse warehouse) {
        int i = warehouseMapper.insert(warehouse);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新仓库
     * 
     * @param warehouse Warehouse类
     * @return Result类
     * 
     *         注意: location字段应使用"纬度 经度"格式字符串，例如: "31.2304 121.4737"
     */
    @PostMapping("/updateWarehouse")
    public Object updateWarehouse(@RequestBody Warehouse warehouse) {
        int i = warehouseMapper.updateById(warehouse);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除仓库
     * 
     * @param warehouseId 仓库ID
     * @return ResultUtil
     */
    @PostMapping("/delWarehouse")
    public Object delWarehouse(Integer warehouseId) {
        int i = warehouseMapper.deleteById(warehouseId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param warehouseSearch 查询类
     * @return ResultUtil
     */
    @RequestMapping("/getAllWarehousesByCon")
    public Object getAllWarehousesByCon(@RequestBody WarehouseSearch warehouseSearch) {
        // 分页对象
        Page<Warehouse> page = new Page<>(warehouseSearch.getPageNo(), warehouseSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();

        if (warehouseSearch.getName() != null && !warehouseSearch.getName().equals("")) {
            wrapper.like("name", warehouseSearch.getName());
        }
        if (warehouseSearch.getEnvironmentZone() != null && !warehouseSearch.getEnvironmentZone().equals("")) {
            wrapper.eq("environment_zone", warehouseSearch.getEnvironmentZone());
        }

        List<Warehouse> warehouses = warehouseMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(warehouses);
    }
}
