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

    /**用户注册**/
    @RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> register(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = null;
        try {
            result = trafficService.register(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**登录验证*/
    @RequestMapping(value = "/logon", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>();
        try {
            result = trafficService.getUser(param);
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**获取首页内容*/
    @RequestMapping(value = "/getAllContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getAllContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.getAllContent(param);
        return result;
    }

    /**查询违章处理*/
    @RequestMapping(value = "/illegalTreatment", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> illegalTreatment(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.illegalTreatment(param);
        return result;
    }

    /**处理违章处理*/
    @RequestMapping(value = "/handle", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> handle(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.handle(param);
        return result;
    }

    /**查询违章信息*/
    @RequestMapping(value = "/getIllegalInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getIllegalInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.getIllegalInfo(param);
        return result;
    }

    /**新增车辆挂牌*/
    @RequestMapping(value = "/insertPlateInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> insertPlateInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.insertPlateInfo(param);
        return result;
    }

    /**查询车辆挂牌信息*/
    @RequestMapping(value = "/selectPlateInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> selectPlateInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.selectPlateInfo(param);
        return result;
    }

    /**查询留言*/
    @RequestMapping(value = "/getLeaveMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getLeaveMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.getLeaveMessage(param);
        return result;
    }

    /**查询留言*/
    @RequestMapping(value = "/insertLeaveMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> insertLeaveMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = trafficService.insertLeaveMessage(param);
        return result;
    }
}
