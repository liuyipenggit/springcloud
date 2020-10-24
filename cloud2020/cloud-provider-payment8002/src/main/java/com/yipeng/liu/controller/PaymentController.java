package com.yipeng.liu.controller;

import com.yipeng.liu.entities.CommonResult;
import com.yipeng.liu.entities.Payment;
import com.yipeng.liu.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.creat(payment);
        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort"+serverPort, result);
        } else {
            return new CommonResult(404, "插入失败,serverPort"+serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort"+serverPort, payment);
        } else {
            return new CommonResult(404, "没有id为：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

}
