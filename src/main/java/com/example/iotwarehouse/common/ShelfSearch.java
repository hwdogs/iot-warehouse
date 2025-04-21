package com.example.iotwarehouse.common;

import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class ShelfSearch {
    private Integer warehouseId;
    private Integer capacityMin;
    private Integer capacityMax;
    private Integer pageNo;
    private Integer pageSize;
}