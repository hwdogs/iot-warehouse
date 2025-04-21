package com.example.iotwarehouse.common;

import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class InventorySearch {
    private Integer goodsId;
    private Integer shelfId;
    private Integer quantityMin;
    private Integer quantityMax;
    private Integer pageNo;
    private Integer pageSize;
}