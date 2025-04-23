package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.request.goodsRequest.AddGoodsRequest;
import com.example.iotwarehouse.common.request.goodsRequest.GoodsSearchRequest;
import com.example.iotwarehouse.common.request.goodsRequest.UpdateGoodsRequest;
import com.example.iotwarehouse.entity.Goods;
import com.example.iotwarehouse.mapper.GoodsMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/goods")
@Tag(name = "商品管理", description = "商品信息的增删改查操作")
public class GoodsController {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 查询所有商品信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllGoods")
    @Operation(summary = "获取所有商品", description = "查询系统中所有商品的信息")
    public ResultUtil getAllGoods() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        return ResultUtil.isSuccess(goodsList);
    }

    /**
     * 添加商品
     * 
     * @param goods Goods类
     * @return Result类
     */
    @PostMapping("/addGoods")
    @Operation(summary = "添加商品", description = "创建新的商品")
    public ResultUtil addGoods(@Parameter(description = "商品信息") @RequestBody AddGoodsRequest request) {
        Goods goods = Goods.builder()
                .rfidTag(request.getRfidTag())
                .category(request.getCategory())
                .weight(request.getWeight())
                .expireDate(request.getExpireDate())
                .build();
        int i = goodsMapper.insert(goods);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新商品
     * 
     * @param request UpdateGoodsRequest类
     * @return Result类
     */
    @PostMapping("/updateGoods")
    @Operation(summary = "更新商品", description = "根据ID更新商品信息")
    public ResultUtil updateGoods(@Parameter(description = "商品信息") @RequestBody UpdateGoodsRequest request) {
        Goods goods = Goods.builder()
                .goodsId(request.getGoodsId())
                .rfidTag(request.getRfidTag())
                .category(request.getCategory())
                .weight(request.getWeight())
                .expireDate(request.getExpireDate())
                .build();
        int i = goodsMapper.updateById(goods);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除商品
     * 
     * @param goodsId 商品ID
     * @return ResultUtil
     */
    @PostMapping("/delGoods")
    @Operation(summary = "删除商品", description = "根据ID删除商品")
    public ResultUtil delGoods(@Parameter(description = "商品ID") @RequestParam Integer goodsId) {
        int i = goodsMapper.deleteById(goodsId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param goodsSearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllGoodsByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询商品信息")
    public ResultUtil getAllGoodsByCon(@Parameter(description = "查询条件") @RequestBody GoodsSearchRequest goodsSearch) {
        // 分页对象
        Page<Goods> page = new Page<>(goodsSearch.getPageNo(), goodsSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();

        if (goodsSearch.getRfidTag() != null && !goodsSearch.getRfidTag().equals("")) {
            wrapper.like("rfid_tag", goodsSearch.getRfidTag());
        }
        if (goodsSearch.getCategory() != null && !goodsSearch.getCategory().equals("")) {
            wrapper.eq("category", goodsSearch.getCategory());
        }
        if (goodsSearch.getExpireDateBefore() != null) {
            wrapper.le("expire_date", goodsSearch.getExpireDateBefore());
        }
        if (goodsSearch.getExpireDateAfter() != null) {
            wrapper.ge("expire_date", goodsSearch.getExpireDateAfter());
        }

        List<Goods> goodsList = goodsMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(goodsList);
    }
}
