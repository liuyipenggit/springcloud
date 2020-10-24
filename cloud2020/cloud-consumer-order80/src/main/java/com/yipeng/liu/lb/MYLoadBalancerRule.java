package com.yipeng.liu.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MYLoadBalancerRule {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
