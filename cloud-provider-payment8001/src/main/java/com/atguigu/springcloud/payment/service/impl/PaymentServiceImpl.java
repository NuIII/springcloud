package com.atguigu.springcloud.payment.service.impl;

import com.atguigu.springcloud.payment.entity.Payment;
import com.atguigu.springcloud.payment.mapper.PaymentMapper;
import com.atguigu.springcloud.payment.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QiCheng.Wang
 * @since 2021-09-03
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}
