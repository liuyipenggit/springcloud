package com.yipeng.liu.dao;

import com.yipeng.liu.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentDao {

    int creat(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
