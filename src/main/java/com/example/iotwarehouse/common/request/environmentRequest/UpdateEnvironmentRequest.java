package com.example.iotwarehouse.common.request.environmentRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author hwshou
 * @date 2025/4/23  23:06
 */
@Data
public class UpdateEnvironmentRequest {
    @Schema(description = "传感器ID", example = "2001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sensorId;

    @Schema(description = "数据采集时间", example = "2025-04-21 17:45:04", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp;

    @Schema(description = "温度（摄氏度）", example = "31.2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal temperature;

    @Schema(description = "湿度（%）", example = "55.1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal humidity;

    @Schema(description = "光照强度", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer light;

    @Schema(description = "气体数据", example = "23488", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String gas;

    @Schema(description = "备注信息", example = "更新实验", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String note;
}
