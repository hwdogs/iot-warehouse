package com.example.iotwarehouse.common.request.deviceRequest;

import lombok.Data;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class DeviceSearchRequest {
    @Schema(description = "设备类型/名称")
    private String type;

    @Schema(description = "设备状态", example = "运行中", allowableValues = { "运行中", "维护中",
            "离线" })
    private String status;

    @Schema(description = "在最后维护日期之后")
    private LocalDate lastMaintenanceAfter;

    @Schema(description = "在最后维护日期之前")
    private LocalDate lastMaintenanceBefore;

    @Schema(description = "第几页", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}
