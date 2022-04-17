package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback")
    public BaseResponse fallback(@PathVariable("id") Long id) {
        DataResponse result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, DataResponse.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常 ....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该 ID 没有对应记录 , 空指针异常 ");
        }

        return result;
    }

    public BaseResponse handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return ResponseBuilder.failed(payment, 444, " 兜底异常 handlerFallback,exception 内容  " + e.getMessage());
    }

    public BaseResponse blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return ResponseBuilder.failed(payment, 445, "blockHandler-sentinel 限流 , 无此流水 : blockException " + blockException.getMessage());
    }


    //==================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    @SentinelResource(value = "fallback2", blockHandler = "blockHandler")
    public BaseResponse paymentSQL(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new RuntimeException(" 没有该 id");
        }
        return paymentService.paymentSQL(id);
    }
}

