package com.example.iotwarehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotwarehouse.common.ResultCode;
import com.example.iotwarehouse.common.DeviceSearch;
import com.example.iotwarehouse.entity.Device;
import com.example.iotwarehouse.mapper.DeviceMapper;
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
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceMapper deviceMapper;

    /**
     * 查询所有设备信息
     * 
     * @return Result类
     */
    @GetMapping("/getAllDevices")
    public Object getAllDevices() {
        List<Device> devices = deviceMapper.selectList(null);
        return ResultUtil.isSuccess(devices);
    }

    /**
     * 添加设备
     * 
     * @param device Device类
     * @return Result类
     */
    @PostMapping("/addDevice")
    public Object addDevice(@RequestBody Device device) {
        int i = deviceMapper.insert(device);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.ADD_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.ADD_FAIL.getCode(), ResultCode.ADD_FAIL.getMsg());
        }
    }

    /**
     * 根据ID更新设备
     * 
     * @param device Device类
     * @return Result类
     */
    @PostMapping("/updateDevice")
    public Object updateDevice(@RequestBody Device device) {
        int i = deviceMapper.updateById(device);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.UPDATE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.UPDATE_FAIL.getCode(), ResultCode.UPDATE_FAIL.getMsg());
        }
    }

    /**
     * 根据ID删除设备
     * 
     * @param deviceId 设备ID
     * @return ResultUtil
     */
    @PostMapping("/delDevice")
    public Object delDevice(Integer deviceId) {
        int i = deviceMapper.deleteById(deviceId);
        if (i == 1) {
            return ResultUtil.isSuccess(ResultCode.DELETE_SUCCESS.getMsg(), null);
        } else {
            return ResultUtil.isFail(ResultCode.DELETE_FAIL.getCode(), ResultCode.DELETE_FAIL.getMsg());
        }
    }

    /**
     * 多条件分页查询
     * 
     * @param deviceSearch 查询类
     * @return ResultUtil
     */
    @PostMapping("/getAllDevicesByCon")
    public Object getAllDevicesByCon(@RequestBody DeviceSearch deviceSearch) {
        // 分页对象
        Page<Device> page = new Page<>(deviceSearch.getPageNo(), deviceSearch.getPageSize());
        // 条件构造器
        QueryWrapper<Device> wrapper = new QueryWrapper<>();

        if (deviceSearch.getType() != null && !deviceSearch.getType().equals("")) {
            wrapper.eq("type", deviceSearch.getType());
        }
        if (deviceSearch.getStatus() != null && !deviceSearch.getStatus().equals("")) {
            wrapper.eq("status", deviceSearch.getStatus());
        }
        if (deviceSearch.getLastMaintenanceAfter() != null) {
            wrapper.ge("last_maintenance", deviceSearch.getLastMaintenanceAfter());
        }
        if (deviceSearch.getLastMaintenanceBefore() != null) {
            wrapper.le("last_maintenance", deviceSearch.getLastMaintenanceBefore());
        }

        List<Device> devices = deviceMapper.selectList(page, wrapper);
        return ResultUtil.isSuccess(devices);
    }
}
