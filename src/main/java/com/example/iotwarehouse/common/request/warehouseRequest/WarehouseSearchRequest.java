package com.example.iotwarehouse.common.request.warehouseRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class WarehouseSearchRequest {
    @Schema(description = "仓库名字")
    private String name;

    @Schema(description = "仓库环境区", example = "常温区", allowableValues = { "常温区", "冷链区", "危险品区" })
    private String environmentZone;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}