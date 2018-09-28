package com.nuc.zigbee.global;

import com.nuc.zigbee.biz.SensorBiz;
import com.nuc.zigbee.entity.ErrorSensor;
import com.nuc.zigbee.entity.Sensor;
import com.nuc.zigbee.serialprot.SerialPortUtils;
import com.nuc.zigbee.utils.DBUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialPortListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final SerialPortUtils serialPort = SerialPortUtils.getIsSerialPort();
        serialPort.openSerialPort();
        final SensorBiz sensorBiz = null;
        class StorageData implements Runnable {

            private double humi;
            private double temp;
            private double light;

            public StorageData(String temp, String humi, String light) {
                String[] humis = humi.split(" ");
                int humiCal = Integer.parseInt(humis[1] + humis[0], 16);
                this.humi = humiCal / 100.0;

                String[] temps = temp.split(" ");
                int tempCal = Integer.parseInt(temps[1] + temps[0], 16);
                this.temp = tempCal / 100.0;

                String[] lights = light.split(" ");
                int lightCal = Integer.parseInt(lights[1] + lights[0], 16);
                this.light = lightCal / 100.0;
            }

            @Override
            public void run() {
                StorageData sd = new StorageData(
                        serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_TEMP)),
                        serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_HUMI)),
                        serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_LIGHT)));
                String tempd = String.valueOf(sd.temp);
                String humid = String.valueOf(sd.humi);
                String lightd = String.valueOf(sd.light);
                double error_temp = Double.parseDouble(tempd);
                double error_light = Double.parseDouble(lightd);
                Date date = new Date();
                SimpleDateFormat simDate = new SimpleDateFormat(
                        "yy/MM/dd HH:mm:ss");
                String dated = simDate.format(date);
                String request = "01";
                String EXECUTEB = serialPort.sensor.get(Contant.SENSOR_EXECUTEB).replace(" ", "");//去掉空格
                if (error_temp > 580 | error_light < 200) {
                    String error_info = "";
                    if (error_temp > 580&error_light<200) {
                        error_info = "温度过高，透光度过低";
                    }else if (error_temp > 580 & error_light > 200){
                        error_info = "温度过高";
                    }else if (error_temp < 580 & error_light < 200){
                        error_info = "透光度过低";
                    }
                    if (!serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_EXECUTEB)).equals(request)) {
                        serialPort.sendToPort(EXECUTEB,"09");
                    }
                    ErrorSensor errorSensor = new ErrorSensor(dated, tempd, lightd, error_info);
                   //DBUtils.insertError(errorSensor);
                } else {
                    serialPort.sendToPort(EXECUTEB,"00");
                }

                System.out.println("湿度：" + humid + "。温度：" + tempd + "。时间：" + dated + "。光照度" + lightd);
                Sensor sensor = new Sensor(dated, tempd, humid, lightd);
                //DBUtils.insert(sensor);
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Thread(new StorageData(
                            serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_TEMP)),
                            serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_HUMI)),
                            serialPort.dataAll.get(serialPort.sensor.get(Contant.SENSOR_LIGHT))
                    )).start();
                }
            }
        }).start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SerialPortUtils.closePort(SerialPortUtils.mSerialport);
    }
}
