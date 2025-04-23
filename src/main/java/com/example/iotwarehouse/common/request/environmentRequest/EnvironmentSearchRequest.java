package com.example.iotwarehouse.common.request.environmentRequest;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class EnvironmentSearchRequest {
    @Schema(description = "传感器ID")
    private Integer sensorId;

    @Schema(description = "结束时间")
    private LocalDateTime timestampStart;

    @Schema(description = "开始时间")
    private LocalDateTime timestampEnd;

    @Schema(description = "最小温度")
    private BigDecimal temperatureMin;

    @Schema(description = "最大温度")
    private BigDecimal temperatureMax;

    @Schema(description = "最小湿度")
    private BigDecimal humidityMin;

    @Schema(description = "最大湿度")
    private BigDecimal humidityMax;

    @Schema(description = "最小光照")
    private Integer lightMin;

    @Schema(description = "最大光照")
    private Integer lightMax;

    @Schema(description = "气体信息")
    private String gas;

    @Schema(description = "第几页", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}