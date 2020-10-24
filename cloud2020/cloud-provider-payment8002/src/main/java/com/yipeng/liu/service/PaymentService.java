package com.yipeng.liu.service;

import com.yipeng.liu.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int creat(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
