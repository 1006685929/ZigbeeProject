package com.nuc.zigbee.dao;

import com.nuc.zigbee.entity.Sensor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("sensorDao")
public interface SensorDao {
    void insert(Sensor sensor);
    void delete(Integer id);
    List<Sensor> selectAll();
}
