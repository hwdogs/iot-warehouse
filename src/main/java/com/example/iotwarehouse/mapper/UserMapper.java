package com.example.iotwarehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iotwarehouse.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return User
     */
    User selectByUsername(
            @Param("username") String username
    );

    /**
     * 根据用户名和密码查询
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User selectByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );

    /**
     * 根据邮箱查询
     * @param email 邮箱
     * @return User
     */
    Integer selectCountByEmail(
            @Param("email") String email
    );

    /**
     * 根据邮箱查询密码
     * @param email 邮箱
     * @param password 密码
     * @return User
     */
    int updatePasswordByEmail(
            @Param("email") String email,
            @Param("password") String password
    );

}

