package com.nuc.zigbee.biz.impl;

import com.nuc.zigbee.biz.SensorBiz;
import com.nuc.zigbee.dao.SensorDao;
import com.nuc.zigbee.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("sensorBiz")
public class SensorBizImpl implements SensorBiz {

    @Autowired
    private SensorDao sensorDao;

    public void add(Sensor sensor) {
        sensorDao.insert(sensor);
    }

    public void remove(Integer id) {
        sensorDao.delete(id);
    }

    public List<Sensor> getAll() {
        return sensorDao.selectAll();
    }
}
