package com.example.iotwarehouse.common;

import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21  12:43
 */
@Data
public class UserSearch {
    private String username;//根据用户名关键字

    private String role;//根据角色

    private Integer pageNo;//当前页

    private Integer pageSize;//每页显示的条数
}
