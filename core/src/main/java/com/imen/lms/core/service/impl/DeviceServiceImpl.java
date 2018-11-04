package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Device;
import com.imen.lms.core.service.IDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @autor LIGANG
 * @data 2018/10/28 11:18
 * @description
 */
@Service
@Transactional
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements IDeviceService{
}
