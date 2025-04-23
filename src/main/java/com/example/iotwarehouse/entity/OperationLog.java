package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志实体类
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "操作日志实体")
@TableName("operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.ASSIGN_ID)
    @Schema(description = "雪花算法日志ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer logId;

    @Schema(description = "操作类型", example = "入库", allowableValues = { "入库", "出库",
            "调拨" }, requiredMode = RequiredMode.REQUIRED)
    private String operationType;

    @Schema(description = "商品ID", example = "12345", requiredMode = RequiredMode.REQUIRED)
    private Integer goodsId;

    @Schema(description = "设备ID", example = "1001")
    private Integer deviceId;

    @Schema(description = "操作人ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Integer operatorId;

    @Schema(description = "操作时间", example = "2025-04-21 19:03:09")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除：0-未删除，1-已删除", example = "0", hidden = true, requiredMode = RequiredMode.REQUIRED)
    private Integer deleted = 0;
}
