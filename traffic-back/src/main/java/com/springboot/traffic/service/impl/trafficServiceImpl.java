package com.springboot.traffic.service.impl;

import com.springboot.traffic.dao.trafficMapper;
import com.springboot.traffic.service.trafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class trafficServiceImpl implements trafficService {
    @Autowired
    trafficMapper trafficMapper;

    /**
     * 用户注册
     * @param 用户名、密码、手机号
     * @return 成功与否、数据
     */
    @Override
    public Map<String, Object> register(Map<String, Object> param) {
        Map<String,Object> result = new HashMap<>();
        int res = 0;
        try {
            res = trafficMapper.userRegister(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success",true);
        result.put("data",res);
        return result;
    }

    /**
     * 用户登录
     * @param 用户名、密码
     * @return 用户是否注册
     */
    @Override
    public Map<String, Object> getUser(Map<String, Object> param) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> res = null;
        try {
            res = trafficMapper.getUser(param);
        } catch(Exception e){
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }
}
