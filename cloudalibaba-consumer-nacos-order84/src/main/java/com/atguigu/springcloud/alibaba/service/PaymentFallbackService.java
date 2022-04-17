
package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.DataResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

/*** @auther zzyy* @create 2019-12-10 17:18*/
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public DataResponse paymentSQL(Long id) {
        return ResponseBuilder.failed(new Payment(id, "errorSerial......"), 444, " 服务降级返回 , 没有该流水信息 ");
    }
}



