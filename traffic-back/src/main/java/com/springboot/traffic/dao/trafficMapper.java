package com.springboot.traffic.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface trafficMapper {
    List<Map<String, Object>> getUser();
}
