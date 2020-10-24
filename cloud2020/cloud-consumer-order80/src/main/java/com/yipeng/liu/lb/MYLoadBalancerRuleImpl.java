package com.yipeng.liu.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MYLoadBalancerRuleImpl implements MYLoadBalancerRule {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        return serviceInstances.get(getAndIncrement(serviceInstances.size()));
    }

    public final int getAndIncrement(int modulo) {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = (current + 1) % modulo;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("调用MYLoadBalancer次数"+(current + 1));
        return next;
    }
}
