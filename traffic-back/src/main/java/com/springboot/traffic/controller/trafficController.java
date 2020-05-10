package com.springboot.traffic.controller;

import com.springboot.traffic.service.trafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/traffic")
public class trafficController {

    @Autowired
    trafficService trafficService;

    //登录验证
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> hello(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>();
        result = trafficService.getUser();
        return result;
    }

}
