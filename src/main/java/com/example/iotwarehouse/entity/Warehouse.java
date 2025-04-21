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
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 地理坐标
     */
    private byte[] location;

    /**
     * 面积（平方米）
     */
    private BigDecimal area;

    private String environmentZone;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLocation() {
        return location;
    }

    public void setLocation(byte[] location) {
        this.location = location;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getEnvironmentZone() {
        return environmentZone;
    }

    public void setEnvironmentZone(String environmentZone) {
        this.environmentZone = environmentZone;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "warehouseId = " + warehouseId +
            ", name = " + name +
            ", location = " + location +
            ", area = " + area +
            ", environmentZone = " + environmentZone +
        "}";
    }
}
