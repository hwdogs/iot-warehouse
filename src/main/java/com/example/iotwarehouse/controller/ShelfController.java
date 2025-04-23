package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.ShelfSearch;
import com.example.iotwarehouse.entity.Shelf;
import com.example.iotwarehouse.mapper.ShelfMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 货架管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/shelf")
@Tag(name = "货架管理", description = "货架信息的增删改查操作")
public class ShelfController {

    @Resource
    private ShelfMapper shelfMapper;

    /**
     * 查询所有货架信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllShelves")
    @Operation(summary = "获取所有货架", description = "查询系统中所有货架的信息")
    public ResultUtil getAllShelves() {
        List<Shelf> shelves = shelfMapper.selectList(null);
        return ResultUtil.isSuccess(shelves);
    }

    /**
     * 添加货架
     * 
     * @param shelf Shelf类
     * @return Result类
     */
    @PostMapping("/addShelf")
    @Operation(summary = "添加货架", description = "创建新的货架")
    public ResultUtil addShelf(@Parameter(description = "货架信息") @RequestBody Shelf shelf) {
        int i = shelfMapper.insert(shelf);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新货架
     * 
     * @param shelf Shelf类
     * @return Result类
     */
    @PostMapping("/updateShelf")
    @Operation(summary = "更新货架", description = "根据ID更新货架信息")
    public ResultUtil updateShelf(@Parameter(description = "货架信息") @RequestBody Shelf shelf) {
        int i = shelfMapper.updateById(shelf);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除货架
     * 
     * @param shelfId 货架ID
     * @return ResultUtil
     */
    @PostMapping("/delShelf")
    @Operation(summary = "删除货架", description = "根据ID删除货架")
    public ResultUtil delShelf(@Parameter(description = "货架ID") @RequestParam Integer shelfId) {
        int i = shelfMapper.deleteById(shelfId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param shelfSearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllShelvesByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询货架信息")
    public ResultUtil getAllShelvesByCon(@Parameter(description = "查询条件") @RequestBody ShelfSearch shelfSearch) {
        // 分页对象
        Page<Shelf> page = new Page<>(shelfSearch.getPageNo(), shelfSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Shelf> wrapper = new QueryWrapper<>();

        if (shelfSearch.getWarehouseId() != null) {
            wrapper.eq("warehouse_id", shelfSearch.getWarehouseId());
        }
        if (shelfSearch.getCapacityMin() != null) {
            wrapper.ge("capacity", shelfSearch.getCapacityMin());
        }
        if (shelfSearch.getCapacityMax() != null) {
            wrapper.le("capacity", shelfSearch.getCapacityMax());
        }

        List<Shelf> shelves = shelfMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(shelves);
    }
}
