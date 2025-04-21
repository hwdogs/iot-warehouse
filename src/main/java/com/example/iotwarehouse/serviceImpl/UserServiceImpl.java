package com.example.iotwarehouse.serviceImpl;

import com.example.iotwarehouse.common.Const;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.entity.User;
import com.example.iotwarehouse.mapper.UserMapper;
import com.example.iotwarehouse.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotwarehouse.util.MD5Util;
import com.example.iotwarehouse.util.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    /**
     * 用户登录逻辑
     *
     * @param username 用户
     * @param password 密码
     * @return ResultUtil
     */
    @Override
    public ResultUtil loginLogic(String username, String password) {
        //step1：判断用户名和密码是否为空
        if (username.isBlank()) {
            return ResultUtil.isFail(ResultCode.USERNAME_EMPTY.getCode(), ResultCode.USERNAME_EMPTY.getMsg());
        }
        if (password.isBlank()) {
            return ResultUtil.isFail(ResultCode.PASSWORD_EMPTY.getCode(), ResultCode.PASSWORD_EMPTY.getMsg());
        }

        //step2：查看用户名是否存在
        if (userMapper.selectByUsername(username) == null) {
            return ResultUtil.isFail(ResultCode.USERNAME_NOT_EXISTS.getCode(), ResultCode.USERNAME_NOT_EXISTS.getMsg());
        }

        //step3：根据用户名和密码查询
        if (userMapper.selectByUsernameAndPassword(username, MD5Util.string2MD5(password)) == null) {
            return ResultUtil.isFail(ResultCode.PASSWORD_ERROR.getCode(), ResultCode.PASSWORD_ERROR.getMsg());
        }
        User user = userMapper.selectByUsername(username);
        user.setPasswordHash(null);
        //step4：成功，返回结果
        return ResultUtil.isSuccess(ResultCode.LOGIN_SUCCESS.getMsg(), user);
    }

    /**
     * 用户注册逻辑
     *
     * @param user User类
     * @return ResultUtil
     */
    @Override
    public ResultUtil registerLogic(User user) {
        //step1：非空校验
        if (user == null) {
            return ResultUtil.isFail(ResultCode.PARAMETER_EMPTY.getCode(), ResultCode.PARAMETER_EMPTY.getMsg());
        }

        String username = user.getUsername();
        String password = user.getPasswordHash();
        String passwordConfirm = user.getPasswordConfirm();
        String email = user.getEmail();
        Integer agree = user.getRole();

        if (username.isBlank()) {
            return ResultUtil.isFail(ResultCode.USERNAME_EMPTY.getCode(), ResultCode.USERNAME_EMPTY.getMsg());
        }
        if (password.isBlank()) {
            return ResultUtil.isFail(ResultCode.PASSWORD_EMPTY.getCode(), ResultCode.PASSWORD_EMPTY.getMsg());
        }
        if (email.isBlank()) {
            return ResultUtil.isFail(ResultCode.EMAIL_EMPTY.getCode(), ResultCode.EMAIL_EMPTY.getMsg());
        }


        //step2：判断两个密码是否相同 不相同则报错
        if (!password.equals(passwordConfirm)) {
            return ResultUtil.isFail(ResultCode.PASSWORD_DIFFERENT.getCode(), ResultCode.PASSWORD_DIFFERENT.getMsg());
        }

        //step3：判断用户名是否存在 存在则报错
        if (userMapper.selectByUsername(username) != null) {
            return ResultUtil.isFail(ResultCode.USERNAME_EXISTS.getCode(), ResultCode.USERNAME_EXISTS.getMsg());    //用户名存在
        }

        //step4：判断邮箱是否存在 存在则报错
        if (userMapper.selectCountByEmail(email) != 0) {
            return ResultUtil.isFail(ResultCode.EMAIL_EXISTS.getCode(), ResultCode.EMAIL_EXISTS.getMsg());   //邮箱存在
        }


        //step5：判断是否勾选用户协议 未同意则报错
        if (agree == null || !agree.equals(Const.WAREHOUSE_ADMIN)) {
            return ResultUtil.isFail(ResultCode.DISAGREEMENT.getCode(), ResultCode.DISAGREEMENT.getMsg());   //未同意用户协议
        }

        //step_final：注册
        user.setPasswordHash(MD5Util.string2MD5(user.getPasswordHash()));   //MD5加密
        user.setPasswordConfirm(MD5Util.string2MD5(user.getPasswordConfirm())); //MD5加密
        user.setRole(Const.WAREHOUSE_ADMIN);    //默认为仓库管理员


        int result = userMapper.insert(user);
        if (result == 0) {
            //注册失败
            return ResultUtil.isFail(ResultCode.REGISTER_FAIL.getCode(), ResultCode.REGISTER_FAIL.getMsg());
        }
        return ResultUtil.isSuccess(ResultCode.REGISTER_SUCCESS.getMsg(), user);
    }

    /**
     * 根据邮箱更改密码逻辑
     * @param email    邮箱
     * @param password 新密码
     * @return ResultUtil
     */
    @Override
    public ResultUtil updatePasswordByEmail(String email, String password) {
        if (email == null) {
            return ResultUtil.isFail(ResultCode.EMAIL_EMPTY.getCode(), ResultCode.EMAIL_EMPTY.getMsg());
        }

        if (password == null) {
            return ResultUtil.isFail(ResultCode.PASSWORD_EMPTY.getCode(), ResultCode.PASSWORD_EMPTY.getMsg());
        }

        int user = userMapper.updatePasswordByEmail(email, MD5Util.string2MD5(password));

        if (user == 0) {
            return ResultUtil.isFail(ResultCode.EMAIL_NOT_EXISTS.getCode(), ResultCode.EMAIL_NOT_EXISTS.getMsg());
        }
        return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg());
    }
}
