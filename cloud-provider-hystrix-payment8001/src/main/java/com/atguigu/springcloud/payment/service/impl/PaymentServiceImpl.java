package com.atguigu.springcloud.payment.service.impl;

import com.atguigu.springcloud.common.exception.ServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author QiCheng.Wang
 * @since 2021-09-03
 */
@Service
public class PaymentServiceImpl {

    public String payment_ok(Long id) {
        return "线程池：" + Thread.currentThread().getName() + "payment_ok,id=" + id;
    }


    @HystrixCommand(
            fallbackMethod = "timeoutFallBack",commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
            }
    )
    public String payment_timeout(Long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        return "线程池：" + Thread.currentThread().getName() + "payment_timeout,id=" + id;
    }

    public String timeoutFallBack(Long id) {
        return "timeoutFallBack o(╥﹏╥)o" + id;
    }


    @HystrixCommand(fallbackMethod ="breakerFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String breaker(Long id) {
        if (id < 0) {
            throw new ServiceException(id + "不能小于0");
        }
        return String.valueOf(id);
    }

    public String breakerFallBack(Long id) {
        return "breakerFallBack o(╥﹏╥)o" + id;
    }
}
