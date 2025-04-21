package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.ASSIGN_ID)
    private Integer goodsId;

    /**
     * UHF RFID标签
     */
    private String rfidTag;

    /**
     * 商品分类
     */
    private Integer category;

    /**
     * 重量（kg）
     */
    private BigDecimal weight;

    /**
     * 保质期
     */
    private LocalDate expireDate;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted = 0;
}
