package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.Goods;
import com.example.iotwarehouse.mapper.GoodsMapper;
import com.example.iotwarehouse.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
