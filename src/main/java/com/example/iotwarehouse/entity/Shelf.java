package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getPositionX() {
        return positionX;
    }

    public void setPositionX(BigDecimal positionX) {
        this.positionX = positionX;
    }

    public BigDecimal getPositionY() {
        return positionY;
    }

    public void setPositionY(BigDecimal positionY) {
        this.positionY = positionY;
    }

    public BigDecimal getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(BigDecimal positionZ) {
        this.positionZ = positionZ;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Shelf{" +
            "shelfId = " + shelfId +
            ", warehouseId = " + warehouseId +
            ", positionX = " + positionX +
            ", positionY = " + positionY +
            ", positionZ = " + positionZ +
            ", capacity = " + capacity +
        "}";
    }
}
