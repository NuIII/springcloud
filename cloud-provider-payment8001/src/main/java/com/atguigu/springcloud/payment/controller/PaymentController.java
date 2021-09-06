package com.atguigu.springcloud.payment.controller;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.payment.service.IPaymentService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public BaseResponse create(@RequestBody Payment payment) {
        paymentService.save(payment);
        return ResponseBuilder.ok(serverPort);
    }


    @GetMapping(value = "/get/{id}")
    public BaseResponse create(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        return ResponseBuilder.ok(payment, "端口号" + serverPort);
    }

    @GetMapping(value = "/discovery")
    public BaseResponse discovery() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("services", discoveryClient.getServices());

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        map.put("instance", instances);

        return ResponseBuilder.ok(map, "端口号" + serverPort);
    }

}

