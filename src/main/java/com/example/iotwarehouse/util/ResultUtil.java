package com.example.iotwarehouse.util;

import com.example.iotwarehouse.common.ResultCode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21  10:48
 */
@Data
public class ResultUtil {
    private Integer code;//状态码

    private Boolean status; //成功或者失败的标识：true,false

    private String msg;// 说明

    private Object data;// 结果数据

    /*
     * 构造参数私有化：外部不能直接通过 new 创建对象了
     * */
    private ResultUtil() {
    }

    private ResultUtil(Integer code, Boolean status, String msg, Object data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回
     */
    public static ResultUtil isSuccess(Object data) {
        return new ResultUtil(ResultCode.OPTION_SUCCESS.getCode(), true, ResultCode.OPTION_SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回,可以传递msg
     */
    public static ResultUtil isSuccess(String msg, Object data) {
        return new ResultUtil(ResultCode.OPTION_SUCCESS.getCode(), true, msg, data);
    }

    /**
     * 失败返回
     */
    public static ResultUtil isFail(Integer code, String msg) {
        return new ResultUtil(code, false, msg, null);
    }

}
