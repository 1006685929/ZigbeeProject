package com.nuc.zigbee.controller;

import com.nuc.zigbee.biz.ErrorSensorBiz;
import com.nuc.zigbee.entity.ErrorSensor;
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

@Controller("errorSensorController")
@RequestMapping("/error")
public class ErrorSensorController {

    @Autowired
    private ErrorSensorBiz errorSensorBiz;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",errorSensorBiz.getAll());
        return "error_list";
    }

    @RequestMapping("/json")
    public void list(HttpServletRequest request, HttpServletResponse response)throws IOException {

        List<ErrorSensor> list = errorSensorBiz.getAll();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<ErrorSensor> list1 = errorSensorBiz.getAll();
        JSONArray jsonArray = JSONArray.fromObject(list1);
        out.print(jsonArray.toString());
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/remove",params = "id")
    public String remove(Integer id){
        errorSensorBiz.remove(id);
        return "redirect:list";
    }

    @RequestMapping("/sendError")
    public void send(HttpServletRequest request, HttpServletResponse response)throws IOException {
        /*
        监听数据库
         */

    }

}
