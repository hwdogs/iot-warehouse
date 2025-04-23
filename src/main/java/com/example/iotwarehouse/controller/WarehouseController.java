
package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.request.warehouseRequest.AddWarehouseRequest;
import com.example.iotwarehouse.common.request.warehouseRequest.UpdateWarehouseRequest;
import com.example.iotwarehouse.common.request.warehouseRequest.WarehouseSearchRequest;
import com.example.iotwarehouse.entity.Warehouse;
import com.example.iotwarehouse.mapper.WarehouseMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/warehouse")
@Tag(name = "仓库管理", description = "仓库信息的增删改查操作")
public class WarehouseController {

    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 查询所有仓库信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllWarehouses")
    @Operation(summary = "获取所有仓库", description = "查询系统中所有仓库的信息")
    public ResultUtil getAllWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectList(null);
        return ResultUtil.isSuccess(warehouses);
    }

    /**
     * 添加仓库
     * 
     * @param request AddWarehouseRequest类
     * @return Result类
     * 
     *         注意: location字段应使用"纬度 经度"格式字符串，例如: "31.2304 121.4737"
     */
    @PostMapping("/addWarehouse")
    @Operation(summary = "添加仓库", description = "创建新的仓库，location字段使用'纬度 经度'格式")
    public ResultUtil addWarehouse(@Parameter(description = "仓库信息") @RequestBody AddWarehouseRequest request) {
        Warehouse warehouse = Warehouse.builder()
                .name(request.getName())
                .location(request.getLocation())
                .area(request.getArea())
                .environmentZone(request.getEnvironmentZone())
                .build();
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
     * @param request UpdateWarehouseRequest类
     * @return Result类
     * 
     *         注意: location字段应使用"纬度 经度"格式字符串，例如: "31.2304 121.4737"
     */
    @PostMapping("/updateWarehouse")
    @Operation(summary = "更新仓库", description = "根据ID更新仓库信息，location字段使用'纬度 经度'格式")
    public ResultUtil updateWarehouse(@Parameter(description = "仓库信息") @RequestBody UpdateWarehouseRequest request) {
        Warehouse warehouse = Warehouse.builder()
                .warehouseId(request.getWarehouseId())
                .name(request.getName())
                .location(request.getLocation())
                .area(request.getArea())
                .environmentZone(request.getEnvironmentZone())
                .build();
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
    @Operation(summary = "删除仓库", description = "根据ID删除仓库")
    public ResultUtil delWarehouse(@Parameter(description = "仓库ID") Integer warehouseId) {
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
     * @param request WarehouseSearchRequest查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllWarehousesByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询仓库信息")
    public ResultUtil getAllWarehousesByCon(
            @Parameter(description = "查询条件") @RequestBody WarehouseSearchRequest request) {
        // 分页对象
        Page<Warehouse> page = new Page<>(request.getPageNo(), request.getPageSize());
        // 条件构造器
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();

        if (request.getName() != null && !request.getName().equals("")) {
            wrapper.like("name", request.getName());
        }
        if (request.getEnvironmentZone() != null && !request.getEnvironmentZone().equals("")) {
            wrapper.eq("environment_zone", request.getEnvironmentZone());
        }

        List<Warehouse> warehouses = warehouseMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(warehouses);
    }
}
