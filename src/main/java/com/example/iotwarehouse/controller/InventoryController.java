package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.InventorySearch;
import com.example.iotwarehouse.entity.Inventory;
import com.example.iotwarehouse.mapper.InventoryMapper;
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
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    private InventoryMapper inventoryMapper;

    /**
     * 查询所有库存信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllInventories")
    public Object getAllInventories() {
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
    public Object addInventory(@RequestBody Inventory inventory) {
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
    public Object updateInventory(@RequestBody Inventory inventory) {
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
    public Object delInventory(Integer inventoryId) {
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
    @GetMapping("/getAllInventoriesByCon")
    public Object getAllInventoriesByCon(@RequestBody InventorySearch inventorySearch) {
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
