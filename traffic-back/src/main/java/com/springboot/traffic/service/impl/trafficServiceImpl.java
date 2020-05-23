package com.springboot.traffic.service.impl;

import com.springboot.traffic.dao.trafficMapper;
import com.springboot.traffic.service.trafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class trafficServiceImpl implements trafficService {
    @Resource
    trafficMapper trafficMapper;

    /**
     * 用户注册
     * @param
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
     * @param
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
    @Override
    public Map<String, Object> getAllContent(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> schoolNoticeList = trafficMapper.getSchoolNotice(param);
        res.put("schoolNoticeList",schoolNoticeList);
        List<Map<String,Object>> parentAdvice = trafficMapper.getParentAdvice(param);
        res.put("parentAdvice",parentAdvice);
        List<Map<String,Object>> studentThinking = trafficMapper.getStudentThinking(param);
        res.put("studentThinking",studentThinking);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> illegalTreatment(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = null;
        try {
            res = trafficMapper.illegalTreatment(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> handle(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = trafficMapper.handle(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> getIllegalInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = null;
        try {
            if ("1".equals(param.get("status"))) {
                res = trafficMapper.getScrapInfo(param);
            } else {
                res = trafficMapper.getIllegalInfo(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> insertPlateInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = trafficMapper.insertPlateInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> selectPlateInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = null;
        try {
            res = trafficMapper.selectPlateInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> getLeaveMessage(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = null;
        try {
            res = trafficMapper.getLeaveMessage(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

    @Override
    public Map<String, Object> insertLeaveMessage(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = trafficMapper.insertLeaveMessage(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("data", res);
        return result;
    }

}
