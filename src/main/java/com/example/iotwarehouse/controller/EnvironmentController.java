package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.request.environmentRequest.EnvironmentSearchRequest;
import com.example.iotwarehouse.entity.Environment;
import com.example.iotwarehouse.mapper.EnvironmentMapper;
import com.example.iotwarehouse.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 环境监控前端控制器
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@RestController
@RequestMapping("/environment")
@Tag(name = "环境监控", description = "环境数据的增删改查操作")
public class EnvironmentController {

    @Resource
    private EnvironmentMapper environmentMapper;

    /**
     * 查询所有环境数据
     *
     * @return Result类
     */
    @GetMapping("/getAllEnvironments")
    @Operation(summary = "获取所有环境数据", description = "查询系统中所有环境监控的数据")
    public ResultUtil getAllEnvironments() {
        List<Environment> environments = environmentMapper.selectList(null);
        return ResultUtil.isSuccess(environments);
    }

    /**
     * 添加环境数据
     *
     * @param environment Environment类
     * @return Result类
     */
    @PostMapping("/addEnvironment")
    @Operation(summary = "添加环境数据", description = "创建新的环境监控数据记录")
    public ResultUtil addEnvironment(@Parameter(description = "环境数据信息") @RequestBody Environment environment) {
        // 设置当前时间作为时间戳，避免timestamp为null
        if (environment.getTimestamp() == null) {
            environment.setTimestamp(LocalDateTime.now());
        }
        int i = environmentMapper.insert(environment);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 更新环境数据
     *
     * @param environment Environment类
     * @return Result类
     */
    @PostMapping("/updateEnvironment")
    @Operation(summary = "更新环境数据", description = "根据传感器ID和时间戳更新环境数据信息")
    public ResultUtil updateEnvironment(@Parameter(description = "环境数据信息") @RequestBody Environment environment) {
        QueryWrapper<Environment> wrapper = new QueryWrapper<>();
        wrapper.eq("sensor_id", environment.getSensorId())
                .eq("timestamp", environment.getTimestamp());
        int i = environmentMapper.update(environment, wrapper);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 删除环境数据
     *
     * @param sensorId  传感器ID
     * @param timestamp 时间戳
     * @return ResultUtil
     */
    @PostMapping("/delEnvironment")
    @Operation(summary = "删除环境数据", description = "根据传感器ID和时间戳删除环境数据")
    public ResultUtil delEnvironment(
            @Parameter(description = "传感器ID") @RequestParam Integer sensorId,
            @Parameter(description = "时间戳") @RequestParam String timestamp) {
        QueryWrapper<Environment> wrapper = new QueryWrapper<>();
        wrapper.eq("sensor_id", sensorId)
                .eq("timestamp", timestamp);
        int i = environmentMapper.delete(wrapper);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     *
     * @param environmentSearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllEnvironmentsByCon")
    @Operation(summary = "条件查询", description = "根据条件分页查询环境数据信息")
    public ResultUtil getAllEnvironmentsByCon(
            @Parameter(description = "查询条件") @RequestBody EnvironmentSearchRequest environmentSearch) {
        // 分页对象
        Page<Environment> page = new Page<>(environmentSearch.getPageNo(), environmentSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Environment> wrapper = new QueryWrapper<>();

        if (environmentSearch.getSensorId() != null) {
            wrapper.eq("sensor_id", environmentSearch.getSensorId());
        }
        if (environmentSearch.getTimestampStart() != null) {
            wrapper.ge("timestamp", environmentSearch.getTimestampStart());
        }
        if (environmentSearch.getTimestampEnd() != null) {
            wrapper.le("timestamp", environmentSearch.getTimestampEnd());
        }
        if (environmentSearch.getTemperatureMin() != null) {
            wrapper.ge("temperature", environmentSearch.getTemperatureMin());
        }
        if (environmentSearch.getTemperatureMax() != null) {
            wrapper.le("temperature", environmentSearch.getTemperatureMax());
        }
        if (environmentSearch.getHumidityMin() != null) {
            wrapper.ge("humidity", environmentSearch.getHumidityMin());
        }
        if (environmentSearch.getHumidityMax() != null) {
            wrapper.le("humidity", environmentSearch.getHumidityMax());
        }
        if (environmentSearch.getLightMin() != null) {
            wrapper.ge("light", environmentSearch.getLightMin());
        }
        if (environmentSearch.getLightMax() != null) {
            wrapper.le("light", environmentSearch.getLightMax());
        }
        if (environmentSearch.getGas() != null) {
            wrapper.eq("gas", environmentSearch.getGas());
        }

        List<Environment> environments = environmentMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(environments);
    }
}
