package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.springcloud.service.PaymentFeignHystrixService;

import javax.annotation.Resource;

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
public class PaymentFeignHystrixController {

    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping(value = "/customer/feign/hystrix/timeout/{id}")
//    @HystrixCommand(
//            fallbackMethod = "timeoutFallBack", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//    }
//    )
    public BaseResponse timeout(@PathVariable("id") Long id) {
        return paymentFeignHystrixService.timeout(id);
    }

//    public BaseResponse timeoutFallBack(Long id) {
//        return ResponseBuilder.ok(80 + "timeoutFallBack");
//    }


    @GetMapping(value = "/customer/feign/hystrix/ok/{id}")
    public BaseResponse ok(@PathVariable("id") Long id) {
        return paymentFeignHystrixService.ok(id);
    }

    @GetMapping(value = "/customer/feign/hystrix/breaker/{id}")
    public DataResponse breaker(@PathVariable("id") Long id) {
        return paymentFeignHystrixService.breaker(id);
    }
}

