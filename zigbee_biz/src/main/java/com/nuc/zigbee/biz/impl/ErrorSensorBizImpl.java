package com.nuc.zigbee.biz.impl;

import com.nuc.zigbee.biz.ErrorSensorBiz;
import com.nuc.zigbee.dao.ErrorSensorDao;
import com.nuc.zigbee.entity.ErrorSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("errorSensorBiz")
public class ErrorSensorBizImpl implements ErrorSensorBiz {

    @Autowired
    private ErrorSensorDao errorSensorDao;

    public void add(ErrorSensor errorSensor) {
        errorSensorDao.insert(errorSensor);
    }

    public void remove(Integer id) {
        errorSensorDao.delete(id);
    }

    public List<ErrorSensor> getAll() {
        return errorSensorDao.selectAll();
    }
}
