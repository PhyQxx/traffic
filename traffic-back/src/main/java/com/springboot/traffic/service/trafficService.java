package com.springboot.traffic.service;

import java.util.Map;

public interface trafficService {
    
    /**用户注册**/
    Map<String, Object> register(Map<String, Object> param);

    /**
     *
     * @return
     */
    Map<String, Object> getUser();


}
