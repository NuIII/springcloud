package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/sendMessage")
    public BaseResponse sendMessage(){
        iMessageProvider.send();
        return ResponseBuilder.ok();
    }
}
