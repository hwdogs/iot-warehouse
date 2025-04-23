package com.example.iotwarehouse.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class ShelfSearch {
    @Schema(description = "仓库名称")
    private Integer warehouseId;

    @Schema(description = "最小载货量")
    private Integer capacityMin;

    @Schema(description = "最大载货量")
    private Integer capacityMax;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}