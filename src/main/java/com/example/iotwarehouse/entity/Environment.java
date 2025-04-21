package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
public class Environment implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField("sensor_id")
    private Integer sensorId;

    @MppMultiId
    @TableField("timestamp")
    private LocalDateTime timestamp;

    private BigDecimal temperature;

    private BigDecimal humidity;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Environment{" +
            "sensorId = " + sensorId +
            ", timestamp = " + timestamp +
            ", temperature = " + temperature +
            ", humidity = " + humidity +
        "}";
    }
}
