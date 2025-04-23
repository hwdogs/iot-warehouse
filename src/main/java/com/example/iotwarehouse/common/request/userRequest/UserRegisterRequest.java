package com.example.iotwarehouse.common.request.userRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/23  21:12
 */
@Data
public class UserRegisterRequest {
    @Schema(description = "用户名", example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "加密密码", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordHash;

    @Schema(description = "确认密码", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;

    @Schema(description = "用户邮箱", example = "example@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "生物特征加密令牌，如指纹", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private byte[] biometricToken;

    @Schema(description = "用户角色", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer role;
}
