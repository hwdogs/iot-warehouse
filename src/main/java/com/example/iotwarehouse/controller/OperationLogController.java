package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.OperationLogSearch;
import com.example.iotwarehouse.entity.OperationLog;
import com.example.iotwarehouse.mapper.OperationLogMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 操作日志管理前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/operationLog")
@Tag(name = "操作日志管理", description = "操作日志信息的增删改查操作")
public class OperationLogController {

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * 查询所有操作日志
     * 
     * @return Result类
     */
    @GetMapping("/getAllOperationLogs")
    @Operation(summary = "获取所有操作日志", description = "查询系统中所有操作日志的信息")
    public ResultUtil getAllOperationLogs() {
        List<OperationLog> logs = operationLogMapper.selectList(null);
        return ResultUtil.isSuccess(logs);
    }

    /**
     * 添加操作日志
     * 
     * @param operationLog OperationLog类
     * @return Result类
     */
    @PostMapping("/addOperationLog")
    @Operation(summary = "添加操作日志", description = "创建新的操作日志记录")
    public ResultUtil addOperationLog(@Parameter(description = "操作日志信息") @RequestBody OperationLog operationLog) {
        int i = operationLogMapper.insert(operationLog);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新操作日志
     * 
     * @param operationLog OperationLog类
     * @return Result类
     */
    @PostMapping("/updateOperationLog")
    @Operation(summary = "更新操作日志", description = "根据ID更新操作日志信息")
    public ResultUtil updateOperationLog(@Parameter(description = "操作日志信息") @RequestBody OperationLog operationLog) {
        int i = operationLogMapper.updateById(operationLog);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除操作日志
     * 
     * @param logId 日志ID
     * @return ResultUtil
     */
    @PostMapping("/delOperationLog")
    @Operation(summary = "删除操作日志", description = "根据ID删除操作日志")
    public ResultUtil delOperationLog(@Parameter(description = "日志ID") @RequestParam Integer logId) {
        int i = operationLogMapper.deleteById(logId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param operationLogSearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllOperationLogsByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询操作日志信息")
    public ResultUtil getAllOperationLogsByCon(
            @Parameter(description = "查询条件") @RequestBody OperationLogSearch operationLogSearch) {
        // 分页对象
        Page<OperationLog> page = new Page<>(operationLogSearch.getPageNo(), operationLogSearch.getPageSize());
        // 条件构造器
        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();

        if (operationLogSearch.getOperationType() != null && !operationLogSearch.getOperationType().equals("")) {
            wrapper.eq("operation_type", operationLogSearch.getOperationType());
        }
        if (operationLogSearch.getGoodsId() != null) {
            wrapper.eq("goods_id", operationLogSearch.getGoodsId());
        }
        if (operationLogSearch.getDeviceId() != null) {
            wrapper.eq("device_id", operationLogSearch.getDeviceId());
        }
        if (operationLogSearch.getOperatorId() != null) {
            wrapper.eq("operator_id", operationLogSearch.getOperatorId());
        }
        if (operationLogSearch.getTimestampStart() != null) {
            wrapper.ge("timestamp", operationLogSearch.getTimestampStart());
        }
        if (operationLogSearch.getTimestampEnd() != null) {
            wrapper.le("timestamp", operationLogSearch.getTimestampEnd());
        }

        List<OperationLog> logs = operationLogMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(logs);
    }
}
