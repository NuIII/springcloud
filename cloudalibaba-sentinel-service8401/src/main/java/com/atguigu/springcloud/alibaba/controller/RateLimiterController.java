package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import handler.GlobalHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl", blockHandler = "handleException")
    public BaseResponse byUrl() {
        return ResponseBuilder.ok(" 按 url 限流测试 OK");
    }

    public BaseResponse handleException(BlockException exception) {
        return ResponseBuilder.failed(exception.getClass().getCanonicalName() + "rateLimit限流");
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = GlobalHandler.class,
            blockHandler = "handler")
    public BaseResponse customerBlockHandler() {
        return ResponseBuilder.ok("customerBlockHandler");
    }

}
