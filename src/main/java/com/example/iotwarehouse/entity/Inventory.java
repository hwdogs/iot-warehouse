package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 库存实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Schema(description = "库存实体")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inventory_id", type = IdType.AUTO)
    @Schema(description = "自增库存ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer inventoryId;

    @Schema(description = "商品ID", example = "12345", requiredMode = RequiredMode.REQUIRED)
    private Integer goodsId;

    @Schema(description = "货架ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer shelfId;

    /**
     * 库存数量
     */
    @Schema(description = "库存数量", example = "201")
    private Integer quantity;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
