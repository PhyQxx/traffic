package com.springboot.traffic.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface trafficMapper {

    /**用户注册
     * @param param**/
    int userRegister(Map<String, Object> param);

    List<Map<String, Object>> getUser();


}
