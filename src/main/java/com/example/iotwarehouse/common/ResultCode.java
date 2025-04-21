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

    ADD_FAIL(500,"添加失败"),
    UPDATE_FAIL(500,"更新失败"),
    DELETE_FAIL(500,"删除失败");






    private final Integer code;
    private final String msg;
}
