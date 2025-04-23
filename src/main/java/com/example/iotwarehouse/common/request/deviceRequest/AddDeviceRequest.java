package com.example.iotwarehouse.common.request.deviceRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author hwshou
 * @date 2025/4/23  23:06
 */
@Data
public class AddDeviceRequest {
    @Schema(description = "自设唯一设备ID", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer deviceId;

    @Schema(description = "设备类型", example = "AGV", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "设备状态", example = "运行中", allowableValues = {"运行中", "维护中",
            "离线"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "最后维护日期", example = "2025-04-01", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDate lastMaintenance;
}
