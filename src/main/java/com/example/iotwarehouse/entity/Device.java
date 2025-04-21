package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("device_id")
    private Integer deviceId;

    private String type;

    private String status;

    private LocalDate lastMaintenance;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    @Override
    public String toString() {
        return "Device{" +
            "deviceId = " + deviceId +
            ", type = " + type +
            ", status = " + status +
            ", lastMaintenance = " + lastMaintenance +
        "}";
    }
}
