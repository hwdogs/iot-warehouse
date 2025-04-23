package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 设备实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "设备实体")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("device_id")
    @Schema(description = "自设唯一设备ID", example = "1001", requiredMode = RequiredMode.REQUIRED)
    private Integer deviceId;

    @Schema(description = "设备类型", example = "AGV", requiredMode = RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "设备状态", example = "运行中", allowableValues = { "运行中", "维护中",
            "离线" }, requiredMode = RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "最后维护日期", example = "2025-04-01")
    private LocalDate lastMaintenance;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
