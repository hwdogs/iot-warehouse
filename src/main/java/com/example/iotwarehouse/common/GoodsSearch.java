package com.example.iotwarehouse.common;

import lombok.Data;
import java.time.LocalDate;

/**
 * @author hwshou
 * @date 2025/4/21
 */
@Data
public class GoodsSearch {
    private String rfidTag;
    private String category;
    private LocalDate expireDateBefore;
    private LocalDate expireDateAfter;
    private Integer pageNo;
    private Integer pageSize;
}