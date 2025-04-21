package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.OperationLog;
import com.example.iotwarehouse.mapper.OperationLogMapper;
import com.example.iotwarehouse.service.IOperationLogService;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
