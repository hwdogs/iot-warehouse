package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.GoodsSearch;
import com.example.iotwarehouse.entity.Goods;
import com.example.iotwarehouse.mapper.GoodsMapper;
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
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 查询所有商品信息
     * 
     * @return Result类
     */
    @RequestMapping("/getAllGoods")
    public Object getAllGoods() {
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
    public Object addGoods(@RequestBody Goods goods) {
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
     * @param goods Goods类
     * @return Result类
     */
    @PostMapping("/updateGoods")
    public Object updateGoods(@RequestBody Goods goods) {
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
    public Object delGoods(Integer goodsId) {
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
    @RequestMapping("/getAllGoodsByCon")
    public Object getAllGoodsByCon(@RequestBody GoodsSearch goodsSearch) {
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
