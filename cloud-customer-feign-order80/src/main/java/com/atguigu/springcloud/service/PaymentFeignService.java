package com.atguigu.springcloud.service;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE",path = "/payment")
public interface PaymentFeignService {

    @GetMapping(value = "/get/{id}")
    public DataResponse get(@PathVariable("id") Long id);

    @GetMapping(value = "/lb")
    public DataResponse lb();
}
