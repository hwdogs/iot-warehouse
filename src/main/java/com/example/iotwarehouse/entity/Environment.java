package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 环境数据实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@TableName("environment")
@Schema(description = "环境数据实体")
public class Environment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "传感器ID", example = "2001", requiredMode = RequiredMode.REQUIRED)
    @MppMultiId
    @TableField("sensor_id")
    private Integer sensorId;

    @Schema(description = "数据采集时间", example = "2025-04-21 17:45:04", requiredMode = RequiredMode.REQUIRED)
    @MppMultiId
    @TableField(value = "timestamp", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp;

    /**
     * 温度
     */
    @Schema(description = "温度（摄氏度）", example = "31.2")
    private BigDecimal temperature;

    /**
     * 湿度
     */
    @Schema(description = "湿度（%）", example = "55.1")
    private BigDecimal humidity;

    /**
     * 光照强度
     */
    @Schema(description = "光照强度", example = "100")
    private Integer light;

    /**
     * 气体数据
     */
    @Schema(description = "气体数据", example = "23488")
    private String gas;

    /**
     * 注释
     */
    @Schema(description = "备注信息", example = "更新实验")
    private String note;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
