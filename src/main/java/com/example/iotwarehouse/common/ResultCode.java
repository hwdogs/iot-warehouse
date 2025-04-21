package com.example.iotwarehouse.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author hwshou
 * @date 2025/4/21  13:30
 */
@ToString
@AllArgsConstructor
@Getter
public enum ResultCode {
    LOGIN_SUCCESS(20000, "登录成功"),
    REGISTER_SUCCESS(20000, "注册成功"),
    OPTION_SUCCESS(20000,"操作成功"),

    ADD_SUCCESS(20000,"添加成功"),
    UPDATE_SUCCESS(20000,"更新成功"),
    DELETE_SUCCESS(20000,"删除成功"),

    REGISTER_FAIL(500,"注册失败"),

    ADD_FAIL(500,"添加失败"),
    UPDATE_FAIL(500,"更新失败"),
    DELETE_FAIL(500,"删除失败"),

    PARAMETER_EMPTY(0, "参数为空"),
    USERNAME_EMPTY(1, "用户名为空"),
    PASSWORD_EMPTY(2, "密码为空"),
    USERNAME_NOT_EXISTS(3, "用户名不存在"),
    USERNAME_EXISTS(4, "用户名已存在"),
    PASSWORD_ERROR(5, "密码错误"),
    EMAIL_EMPTY(6, "邮箱为空"),
    EMAIL_EXISTS(7, "邮箱已存在"),
    EMAIL_NOT_EXISTS(8,"邮箱不存在"),
    PASSWORD_DIFFERENT(9, "密码不一致"),
    DISAGREEMENT(10, "未同意用户协议");


    private final Integer code;
    private final String msg;
}
