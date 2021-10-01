package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author QiCheng.Wang
 * @since 2021-09-03
 */
@RestController
@RequestMapping("/payment")
public class OrderController {

    public final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping(value = "/customer/create")
    public BaseResponse create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, DataResponse.class);
    }


    @GetMapping(value = "/customer/get/{id}")
    public BaseResponse get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, DataResponse.class);

    }


    @GetMapping(value = "/customer/get2/{id}")
    public BaseResponse get2(@PathVariable("id") Long id) {
        ResponseEntity<DataResponse> responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, DataResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return ResponseBuilder.ok();
    }


    @GetMapping(value = "/customer/lb")
    public BaseResponse lb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return ResponseBuilder.ok();
        }
        ServiceInstance instance = loadBalancer.getInstance(instances);

        ResponseEntity<DataResponse> responseEntity = restTemplate.getForEntity(instance.getUri() + "/payment/lb/", DataResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        return ResponseBuilder.ok();
    }
}


