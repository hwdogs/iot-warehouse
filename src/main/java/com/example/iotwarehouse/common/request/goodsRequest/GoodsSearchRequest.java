package com.example.iotwarehouse.common.request.goodsRequest;

import lombok.Data;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class GoodsSearchRequest {
    @Schema(description = "rfid", example = "123456aaa")
    private String rfidTag;

    @Schema(description = "类别", example = "1")
    private String category;

    @Schema(description = "在到期日期之前", example = "2026-07-12")
    private LocalDate expireDateBefore;

    @Schema(description = "在到期日期之后", example = "2025-07-12")
    private LocalDate expireDateAfter;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}