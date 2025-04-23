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

/**
 * <p>
 * 权限实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "权限实体")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "perm_id", type = IdType.AUTO)
    @Schema(description = "自增权限ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer permId;

    /**
     * 如入库操作
     */
    @Schema(description = "权限名称", example = "入库", requiredMode = RequiredMode.REQUIRED)
    private String permName;

    @Schema(description = "所属模块", example = "库存", allowableValues = { "库存", "设备",
            "报表" }, requiredMode = RequiredMode.REQUIRED)
    private String module;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
