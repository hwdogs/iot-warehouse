package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.Inventory;
import com.example.iotwarehouse.mapper.InventoryMapper;
import com.example.iotwarehouse.service.IInventoryService;
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
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

}
