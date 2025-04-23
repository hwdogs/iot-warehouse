package com.example.iotwarehouse.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21 12:43
 */
@Data
public class UserSearch {
    @Schema(description = "用户名")
    private String username;// 根据用户名关键字

    @Schema(description = "角色")
    private String role;// 根据角色

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}
