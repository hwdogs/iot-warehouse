package com.example.iotwarehouse.common.request.permissionRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/23  23:02
 */
@Data
public class AddPermissionRequest {
    @Schema(description = "权限名称", example = "入库", requiredMode = Schema.RequiredMode.REQUIRED)
    private String permName;

    @Schema(description = "所属模块", example = "库存", allowableValues = { "库存", "设备",
            "报表" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private String module;

}
