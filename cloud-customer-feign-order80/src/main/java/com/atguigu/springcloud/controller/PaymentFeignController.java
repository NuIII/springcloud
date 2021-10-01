package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.service.PaymentFeignService;
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
public class PaymentFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/customer/feign/get/{id}")
    public BaseResponse get(@PathVariable("id") Long id) {
        return paymentFeignService.get(id);
    }

    @GetMapping(value = "/customer/feign//lb")
    public BaseResponse lb(){
        return paymentFeignService.lb();
    }
}

