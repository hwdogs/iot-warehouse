package com.example.iotwarehouse.common.request.shelfRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hwshou
 * @date 2025/4/23  23:01
 */
@Data
public class UpdateSelfRequest {
    @Schema(description = "自增货架ID", example = "459034625", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer shelfId;

    @Schema(description = "X坐标位置", example = "204.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal positionX;

    @Schema(description = "Y坐标位置", example = "104.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal positionY;

    @Schema(description = "Z坐标位置", example = "4.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal positionZ;

    @Schema(description = "最大存货量", example = "99999", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer capacity;
}
