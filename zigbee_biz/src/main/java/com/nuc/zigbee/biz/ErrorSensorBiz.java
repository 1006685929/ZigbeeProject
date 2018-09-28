package com.nuc.zigbee.biz;

import com.nuc.zigbee.entity.ErrorSensor;

import java.util.List;

public interface ErrorSensorBiz {
    void add(ErrorSensor errorSensor);
    void remove(Integer id);
    List<ErrorSensor> getAll();
}
