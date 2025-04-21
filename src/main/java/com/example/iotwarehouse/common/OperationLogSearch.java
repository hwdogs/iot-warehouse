package com.example.iotwarehouse.common;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class OperationLogSearch {
    private String operationType;
    private Integer goodsId;
    private Integer deviceId;
    private Integer operatorId;
    private LocalDateTime timestampStart;
    private LocalDateTime timestampEnd;
    private Integer pageNo;
    private Integer pageSize;
}