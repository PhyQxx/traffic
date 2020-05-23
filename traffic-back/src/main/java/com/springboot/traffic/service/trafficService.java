package com.springboot.traffic.service;

import java.util.Map;

public interface trafficService {

    /**
     * 用户注册
     * @param 用户名、密码、手机号
     * @return 成功与否、数据
     */
    Map<String, Object> register(Map<String, Object> param);

    /**
     * 用户登录
     * @param 用户名、密码
     * @return 用户是否注册
     */
    Map<String, Object> getUser(Map<String, Object> param);

    /**
     * 获取首页内容
     */
    Map<String,Object> getAllContent(Map<String,Object> param);

    Map<String, Object> illegalTreatment(Map<String, Object> param);

    Map<String, Object> handle(Map<String, Object> param);

    Map<String, Object> getIllegalInfo(Map<String, Object> param);

    Map<String, Object> insertPlateInfo(Map<String, Object> param);

    Map<String, Object> selectPlateInfo(Map<String, Object> param);

    Map<String, Object> getLeaveMessage(Map<String, Object> param);

    Map<String, Object> insertLeaveMessage(Map<String, Object> param);
}
