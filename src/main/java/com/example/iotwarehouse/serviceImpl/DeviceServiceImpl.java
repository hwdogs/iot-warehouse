package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.Device;
import com.example.iotwarehouse.mapper.DeviceMapper;
import com.example.iotwarehouse.service.IDeviceService;
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
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

}
