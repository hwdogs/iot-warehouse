package com.example.iotwarehouse.common.request.warehouseRequest;

import com.example.iotwarehouse.entity.Warehouse;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hwshou
 * @date 2025/4/23  22:30
 */
@Data
public class AddWarehouseRequest {
    @Schema(description = "仓库名称", example = "华北中心仓", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonSerialize(using = com.example.iotwarehouse.config.LocationSerializer.class)
    @JsonDeserialize(using = com.example.iotwarehouse.config.LocationDeserializer.class)
    @Schema(description = "地理位置坐标（'纬度 经度'格式）", example = "39.9042 116.4074", requiredMode = Schema.RequiredMode.REQUIRED)
    private byte[] location;

    @Schema(description = "仓库面积", example = "250", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal area;

    @Schema(description = "仓库环境区", example = "危险区", allowableValues = { "常温区", "冷链区",
            "危险品区" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private String environmentZone;

}
