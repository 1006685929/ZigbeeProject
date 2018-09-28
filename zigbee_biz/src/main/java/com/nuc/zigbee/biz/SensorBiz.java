package com.nuc.zigbee.biz;

import com.nuc.zigbee.entity.Sensor;

import java.util.List;

public interface SensorBiz {
    void add(Sensor sensor);
    void remove(Integer id);
    List<Sensor> getAll();
}
