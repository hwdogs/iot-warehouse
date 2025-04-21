package com.example.iotwarehouse.common;

import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class PermissionSearch {
    private String permName;
    private String module;
    private Integer pageNo;
    private Integer pageSize;
}