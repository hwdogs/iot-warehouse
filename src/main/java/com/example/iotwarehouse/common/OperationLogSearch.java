package com.example.iotwarehouse.common;

import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class OperationLogSearch {
    @Schema(description = "操作类型", example = "调拨", allowableValues = { "入库", "出库", "调拨" })
    private String operationType;

    @Schema(description = "货物ID", example = "-1776496639")
    private Integer goodsId;

    @Schema(description = "设备ID", example = "1001")
    private Integer deviceId;

    @Schema(description = "操作者ID", example = "303878146")
    private Integer operatorId;

    @Schema(description = "log结束时间", example = "2025-04-21 19:03:09")
    private LocalDateTime timestampStart;

    @Schema(description = "log开始时间", example = "2025-03-15 18:22:45")
    private LocalDateTime timestampEnd;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}