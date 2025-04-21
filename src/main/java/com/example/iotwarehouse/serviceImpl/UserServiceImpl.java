package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.entity.User;
import com.example.iotwarehouse.mapper.UserMapper;
import com.example.iotwarehouse.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
