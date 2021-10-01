package com.atguigu.springcloud.payment.controller;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.payment.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class PaymentController {

    @Resource
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/hystrix/ok/{id}")
    public BaseResponse payment_ok(@PathVariable("id") Long id) {
        String result = paymentService.payment_ok(id);
        return ResponseBuilder.ok(result, "端口号" + serverPort);
    }

    @GetMapping(value = "/hystrix/timeout/{id}")
    public BaseResponse payment_timeout(@PathVariable("id") Long id) {
        String result = paymentService.payment_timeout(id);
        return ResponseBuilder.ok(result, "端口号" + serverPort);
    }

    /**
     * 功能描述: 熔断测试 （真正的熔断在客户端）
     * @param id
     * @return com.atguigu.springcloud.common.response.DataResponse
     * @author wangQc
     * @date 2021/9/30 4:02 下午
     */
    @GetMapping(value = "/hystrix/breaker/{id}")
    public DataResponse breaker(@PathVariable("id") Long id){
        String result = paymentService.breaker(id);
        return ResponseBuilder.ok(result, "端口号" + serverPort);
    }

}

