package com.springboot.traffic.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface trafficMapper {

    /**
     * 用户注册
     * @param 用户名、密码、手机号
     * @return 1：注册成功 0：注册失败
     */
    int userRegister(Map<String, Object> param);

    /**
     * 用户登录
     * @param 用户名、密码
     * @return 用户是否注册
     */
    List<Map<String, Object>> getUser(Map<String, Object> param);

    /**
     * 获取信息公告
     */
    List<Map<String,Object>> getSchoolNotice(Map<String,Object> param);

    /**
     * 获取交通资讯
     */
    List<Map<String,Object>> getParentAdvice(Map<String,Object> param);

    /**
     * 获取警示教育
     */
    List<Map<String,Object>> getStudentThinking(Map<String,Object> param);

    List<Map<String, Object>> illegalTreatment(Map<String, Object> param);

    int handle(Map<String, Object> param);

    List<Map<String, Object>> getIllegalInfo(Map<String, Object> param);

    List<Map<String, Object>> getScrapInfo(Map<String, Object> param);

    int insertPlateInfo(Map<String, Object> param);

    List<Map<String, Object>> selectPlateInfo(Map<String, Object> param);

    List<Map<String, Object>> getLeaveMessage(Map<String, Object> param);

    int insertLeaveMessage(Map<String, Object> param);
}
