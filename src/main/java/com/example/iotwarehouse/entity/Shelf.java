package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 货架实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Schema(description = "货架实体")
public class Shelf implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shelf_id", type = IdType.AUTO)
    @Schema(description = "自增货架ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer shelfId;

    @Schema(description = "所属仓库ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer warehouseId;

    @Schema(description = "X坐标位置", example = "204.00")
    private BigDecimal positionX;

    @Schema(description = "Y坐标位置", example = "104.00")
    private BigDecimal positionY;

    @Schema(description = "Z坐标位置", example = "4.00")
    private BigDecimal positionZ;

    /**
     * 最大存货量
     */
    @Schema(description = "最大存货量", example = "99999", requiredMode = RequiredMode.REQUIRED)
    private Integer capacity;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
