package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "perm_id", type = IdType.AUTO)
    private Integer permId;

    /**
     * 如入库操作
     */
    private String permName;

    private String module;

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Permission{" +
            "permId = " + permId +
            ", permName = " + permName +
            ", module = " + module +
        "}";
    }
}
