package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;

/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @TableId(value = "warehouse_id", type = IdType.ASSIGN_ID)
    @Schema(description = "雪花算法仓库ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer warehouseId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称", example = "华北中心仓", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 地理坐标，格式: "纬度 经度"，如: "31.2304 121.4737"
     */
    @Schema(description = "地理位置坐标（'纬度 经度'格式）", example = "39.9042 116.4074", requiredMode = Schema.RequiredMode.REQUIRED)
    private byte[] location;

    /**
     * 面积（平方米）
     */
    @Schema(description = "仓库面积", example = "250", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal area;

    @Schema(description = "仓库环境区", example = "危险区", allowableValues = { "常温区", "冷链区",
            "危险品区" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private String environmentZone;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除", hidden = true, requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer deleted = 0;

}
