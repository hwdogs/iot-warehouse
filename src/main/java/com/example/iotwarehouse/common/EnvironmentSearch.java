package com.example.iotwarehouse.common;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class EnvironmentSearch {
    private Integer sensorId;
    private LocalDateTime timestampStart;
    private LocalDateTime timestampEnd;
    private BigDecimal temperatureMin;
    private BigDecimal temperatureMax;
    private BigDecimal humidityMin;
    private BigDecimal humidityMax;
    private Integer lightMin;
    private Integer lightMax;
    private String gas;
    private Integer pageNo;
    private Integer pageSize;
}