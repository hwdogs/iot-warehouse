package com.example.iotwarehouse.common;

import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class WarehouseSearch {
    private String name;
    private String environmentZone;
    private Integer pageNo;
    private Integer pageSize;
}