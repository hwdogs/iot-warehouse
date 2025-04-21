package com.example.iotwarehouse.common;

import lombok.Data;
import java.time.LocalDate;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class DeviceSearch {
    private String type;
    private String status;
    private LocalDate lastMaintenanceAfter;
    private LocalDate lastMaintenanceBefore;
    private Integer pageNo;
    private Integer pageSize;
}