
package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.common.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*** @auther zzyy* @create 2019-12-10 17:17*
 * 使用 fallback 方式是无法获取异常信息的，
 * * 如果想要获取异常信息，可以使用 fallbackFactory 参数
 * */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class) // 调用中关闭 9003 服务提供者
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    DataResponse paymentSQL(@PathVariable("id") Long id);
}

