package com.nuc.zigbee.dao;

import com.nuc.zigbee.entity.ErrorSensor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("errorSensorDao")
public interface ErrorSensorDao {
    void insert(ErrorSensor errorSensor);
    void delete(Integer id);
    List<ErrorSensor> selectAll();
}
