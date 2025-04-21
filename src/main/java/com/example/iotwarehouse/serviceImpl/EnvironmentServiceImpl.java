package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.Environment;
import com.example.iotwarehouse.mapper.EnvironmentMapper;
import com.example.iotwarehouse.service.IEnvironmentService;
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
public class EnvironmentServiceImpl extends ServiceImpl<EnvironmentMapper, Environment> implements IEnvironmentService {

}
