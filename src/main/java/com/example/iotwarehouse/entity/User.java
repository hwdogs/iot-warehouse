package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    @Schema(description = "雪花算法用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    @Schema(description = "用户名", example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "加密密码", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordHash;

    @Schema(description = "确认密码", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;

    /**
     * 邮箱
     */
    @Schema(description = "用户邮箱", example = "example@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    /**
     * 生物特征加密令牌
     */
    @Schema(description = "生物特征加密令牌，如指纹", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private byte[] biometricToken;

    @Schema(description = "用户角色", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer role;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除", hidden = true, requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer deleted;

}
