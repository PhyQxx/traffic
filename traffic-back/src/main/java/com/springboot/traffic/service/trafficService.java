package com.springboot.traffic.service;

import java.util.Map;

public interface trafficService {

    Map<String, Object> register(Map<String, Object> param);

    /**
     *
     * @return
     */
    Map<String, Object> getUser();


}
