package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
public class Shelf implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shelf_id", type = IdType.ASSIGN_ID)
    private Integer shelfId;

    private Integer warehouseId;

    private BigDecimal positionX;

    private BigDecimal positionY;

    private BigDecimal positionZ;

    /**
     * 最大存货量
     */
    private Integer capacity;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted = 0;
}
