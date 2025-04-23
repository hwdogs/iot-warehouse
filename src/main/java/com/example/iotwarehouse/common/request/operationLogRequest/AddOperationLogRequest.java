package com.example.iotwarehouse.common.request.operationLogRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author hwshou
 * @date 2025/4/23  23:03
 */
@Data
public class AddOperationLogRequest {
    @Schema(description = "操作类型", example = "入库", allowableValues = { "入库", "出库",
            "调拨" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private String operationType;

    @Schema(description = "商品ID", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer goodsId;

    @Schema(description = "设备ID", example = "1001", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer deviceId;

    @Schema(description = "操作人ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer operatorId;
}
