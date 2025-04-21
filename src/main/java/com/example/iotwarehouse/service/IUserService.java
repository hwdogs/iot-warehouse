package com.example.iotwarehouse.service;

import com.example.iotwarehouse.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotwarehouse.util.ResultUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Service
public interface IUserService extends IService<User> {

    /**
     * 登录逻辑实现
     *
     * @param username 用户
     * @param password 密码
     * @return ResultUtil
     */
    ResultUtil loginLogic(String username, String password);

    /**
     * 注册逻辑实现
     *
     * @param user 用户
     * @return ResultUtil
     */
    ResultUtil registerLogic(User user);

    ResultUtil updatePasswordByEmail(String email, String password);
}
