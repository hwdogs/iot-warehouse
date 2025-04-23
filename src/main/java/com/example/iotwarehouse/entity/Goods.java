package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 商品实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品实体")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.ASSIGN_ID)
    @Schema(description = "雪花算法商品ID", example = "12345", requiredMode = RequiredMode.REQUIRED)
    private Integer goodsId;

    /**
     * UHF RFID标签
     */
    @Schema(description = "RFID标签", example = "123456aaa", requiredMode = RequiredMode.REQUIRED)
    private String rfidTag;

    /**
     * 商品分类
     */
    @Schema(description = "商品分类编号", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer category;

    /**
     * 重量（kg）
     */
    @Schema(description = "重量（kg）", example = "103.34")
    private BigDecimal weight;

    /**
     * 保质期
     */
    @Schema(description = "保质期", example = "2026-07-12")
    private LocalDate expireDate;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
