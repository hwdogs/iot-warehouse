package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.request.inventoryRequest.InventorySearchRequest;
import com.example.iotwarehouse.entity.Inventory;
import com.example.iotwarehouse.mapper.InventoryMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 库存管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/inventory")
@Tag(name = "库存管理", description = "库存信息的增删改查操作")
public class InventoryController {

    @Resource
    private InventoryMapper inventoryMapper;

    /**
     * 查询所有库存信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllInventories")
    @Operation(summary = "获取所有库存", description = "查询系统中所有库存的信息")
    public ResultUtil getAllInventories() {
        List<Inventory> inventories = inventoryMapper.selectList(null);
        return ResultUtil.isSuccess(inventories);
    }

    /**
     * 添加库存
     * 
     * @param inventory Inventory类
     * @return Result类
     */
    @PostMapping("/addInventory")
    @Operation(summary = "添加库存", description = "创建新的库存记录")
    public ResultUtil addInventory(@Parameter(description = "库存信息") @RequestBody Inventory inventory) {
        int i = inventoryMapper.insert(inventory);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新库存
     * 
     * @param inventory Inventory类
     * @return Result类
     */
    @PostMapping("/updateInventory")
    @Operation(summary = "更新库存", description = "根据ID更新库存信息")
    public ResultUtil updateInventory(@Parameter(description = "库存信息") @RequestBody Inventory inventory) {
        int i = inventoryMapper.updateById(inventory);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除库存
     * 
     * @param inventoryId 库存ID
     * @return ResultUtil
     */
    @PostMapping("/delInventory")
    @Operation(summary = "删除库存", description = "根据ID删除库存记录")
    public ResultUtil delInventory(@Parameter(description = "库存ID") @RequestParam Integer inventoryId) {
        int i = inventoryMapper.deleteById(inventoryId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param inventorySearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllInventoriesByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询库存信息")
    public ResultUtil getAllInventoriesByCon(
            @Parameter(description = "查询条件") @RequestBody InventorySearchRequest inventorySearch) {
        // 分页对象
        Page<Inventory> page = new Page<>(inventorySearch.getPageNo(), inventorySearch.getPageSize());
        // 条件构造器
        QueryWrapper<Inventory> wrapper = new QueryWrapper<>();

        if (inventorySearch.getGoodsId() != null) {
            wrapper.eq("goods_id", inventorySearch.getGoodsId());
        }
        if (inventorySearch.getShelfId() != null) {
            wrapper.eq("shelf_id", inventorySearch.getShelfId());
        }
        if (inventorySearch.getQuantityMin() != null) {
            wrapper.ge("quantity", inventorySearch.getQuantityMin());
        }
        if (inventorySearch.getQuantityMax() != null) {
            wrapper.le("quantity", inventorySearch.getQuantityMax());
        }

        List<Inventory> inventories = inventoryMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(inventories);
    }
}
