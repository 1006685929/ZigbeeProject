package com.nuc.zigbee.controller;

import com.nuc.zigbee.biz.SensorBiz;
import com.nuc.zigbee.entity.Sensor;
import com.nuc.zigbee.global.Contant;
import com.nuc.zigbee.serialprot.SerialPortUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller("sensorController")
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorBiz sensorBiz;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",sensorBiz.getAll());
        return "sensor_list";
    }


    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("sensor",new Sensor());
        return "sensor_add";
    }

    @RequestMapping("/json")
    public void list(HttpServletRequest request, HttpServletResponse response)throws IOException {

        List<Sensor> list = sensorBiz.getAll();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        List<Sensor> list1 = sensorBiz.getAll();
        JSONArray jsonArray = JSONArray.fromObject(list1);
        out.print(jsonArray.toString());
        out.flush();
        out.close();
    }

    @RequestMapping("/add")
    public String add(Sensor sensor){
        sensorBiz.add(sensor);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove",params = "id")
    public String remove(Integer id){
        sensorBiz.remove(id);
        return "redirect:list";
    }

    @RequestMapping("/to_send")
    public String toSend(){
        return "sensor_controller";
    }

    @RequestMapping(value = "/send",params = "request")
    public String send(String request){
        String oldRequest = "00";
        String newRequest = request;
        SerialPortUtils serialPort = SerialPortUtils.getIsSerialPort();
        String EXECUTEB = serialPort.sensor.get(Contant.SENSOR_EXECUTEB).replace(" ","");//去掉空格
        if (request.equals("00")){
            serialPort.sendToPort(EXECUTEB,newRequest);
            return "sensor_controller";
        }
        if (!serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB)).equals(oldRequest)){
            oldRequest = serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB));
            int oldNum = Integer.valueOf(oldRequest);
            int newNum = Integer.valueOf(request);
            Integer tempNum = oldNum|newNum;
            newRequest = "0" + Integer.toHexString(tempNum);
        }
        if (serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB)).equals("09")|
                serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB)).equals("01")|
                serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB)).equals("08")){
            oldRequest = serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB));
            int oldNum = Integer.valueOf(oldRequest);
                int newNum = Integer.valueOf(request);
                Integer tempNum = oldNum^newNum;
            newRequest = "0" + Integer.toHexString(tempNum);
        }
        serialPort.sendToPort(EXECUTEB,newRequest);
        return "sensor_controller";

    }

}
