package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.OperationLogSearch;
import com.example.iotwarehouse.entity.OperationLog;
import com.example.iotwarehouse.mapper.OperationLogMapper;
import com.example.iotwarehouse.util.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * 查询所有操作日志
     * 
     * @return Result类
     */
    @RequestMapping("/getAllOperationLogs")
    public Object getAllOperationLogs() {
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
    public Object addOperationLog(@RequestBody OperationLog operationLog) {
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
    public Object updateOperationLog(@RequestBody OperationLog operationLog) {
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
    public Object delOperationLog(Integer logId) {
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
    @RequestMapping("/getAllOperationLogsByCon")
    public Object getAllOperationLogsByCon(@RequestBody OperationLogSearch operationLogSearch) {
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
