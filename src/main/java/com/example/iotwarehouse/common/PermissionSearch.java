package com.example.iotwarehouse.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class PermissionSearch {
    @Schema(description = "权限名称", example = "入库")
    private String permName;

    @Schema(description = "所属模块('库存''设备''报表')", example = "库存", allowableValues = { "库存", "设备", "报表" })
    private String module;

    @Schema(description = "第几页", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer pageNo;

    @Schema(description = "每页大小", example = "5", requiredMode = RequiredMode.REQUIRED)
    private Integer pageSize;
}