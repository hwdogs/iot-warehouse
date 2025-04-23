package com.example.iotwarehouse.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21 13:59
 */
@Data
public class UserLogin {
    @Schema(description = "用户名", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "密码", requiredMode = RequiredMode.REQUIRED)
    private String password;
}
