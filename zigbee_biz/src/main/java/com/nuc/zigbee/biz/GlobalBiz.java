package com.nuc.zigbee.biz;

import com.nuc.zigbee.entity.Employee;

public interface GlobalBiz {
    Employee login(String sn, String password);
    void changePassword(Employee employee);
}
