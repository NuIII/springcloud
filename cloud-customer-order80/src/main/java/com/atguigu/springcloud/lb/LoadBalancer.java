package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

public interface LoadBalancer {


    /** 取服务器
     * 功能描述:
     * @param serviceInstanceList
     * @return org.springframework.cloud.client.ServiceInstance
     * @author wangQc
     * @date 2021/9/26 10:15 上午
     */
    ServiceInstance getInstance(List<ServiceInstance> serviceInstanceList);
}
