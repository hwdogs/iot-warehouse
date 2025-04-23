package com.example.iotwarehouse.common.request.inventoryRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class InventorySearchRequest {
    @Schema(description = "货物ID", example = "-1776496639")
    private Integer goodsId;

    @Schema(description = "货架ID", example = "3")
    private Integer shelfId;

    @Schema(description = "最小质量", example = "10")
    private Integer quantityMin;

    @Schema(description = "最大质量", example = "500")
    private Integer quantityMax;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}