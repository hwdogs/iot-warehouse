package com.example.iotwarehouse.common.request.inventoryRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/23  23:04
 */
@Data
public class AddInventoryRequest {
    @Schema(description = "商品ID", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer goodsId;

    @Schema(description = "货架ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer shelfId;

    @Schema(description = "库存数量", example = "201", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer quantity;
}
