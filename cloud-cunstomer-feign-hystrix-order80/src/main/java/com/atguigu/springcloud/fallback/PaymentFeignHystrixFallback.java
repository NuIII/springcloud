package com.atguigu.springcloud.fallback;

import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixFallback implements PaymentFeignHystrixService {

    @Override
    public DataResponse timeout(Long id) {
        return ResponseBuilder.ok("o(╥﹏╥)o");
    }

    @Override
    public DataResponse ok(Long id) {
        return ResponseBuilder.ok("o(╥﹏╥)o");
    }

    @Override
    public DataResponse breaker(Long id) {
        return ResponseBuilder.ok("o(╥﹏╥)o breaker");
    }
}
