package com.atguigu.springcloud.service;


import com.atguigu.springcloud.common.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.atguigu.springcloud.fallback.PaymentFeignHystrixFallback;

@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", path = "/payment", fallback = PaymentFeignHystrixFallback.class)
public interface PaymentFeignHystrixService {

    @GetMapping(value = "/hystrix/timeout/{id}")
    public DataResponse timeout(@PathVariable("id") Long id);

    @GetMapping(value = "/hystrix/ok/{id}")
    public DataResponse ok(@PathVariable("id") Long id);


    @GetMapping(value = "/hystrix/breaker/{id}")
    public DataResponse breaker(@PathVariable("id") Long id);

}
