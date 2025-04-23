package com.example.iotwarehouse.common.request.goodsRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author hwshou
 * @date 2025/4/23  23:04
 */
@Data
public class AddGoodsRequest {
    @Schema(description = "RFID标签", example = "123456aaa", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rfidTag;

    @Schema(description = "商品分类编号", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer category;

    @Schema(description = "重量（kg）", example = "103.34", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal weight;

    @Schema(description = "保质期", example = "2026-07-12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDate expireDate;
}
