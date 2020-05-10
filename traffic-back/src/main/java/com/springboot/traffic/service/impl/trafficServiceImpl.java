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

    @Override
    public Map<String, Object> getUser() {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> res = trafficMapper.getUser();
        result.put("success", true);
        result.put("data", res);
        return result;
    }
}
