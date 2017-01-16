package com.potapov.hazelcast.client.config;

import com.hazelcast.client.spi.ClientProxy;
import com.hazelcast.client.spi.ClientProxyFactory;


public class ProxyFactory implements ClientProxyFactory {

    @Override
    public ClientProxy create(String s) {
        return new ClientPriorityQueueProxyImpl("priorityQueueService", s);
    }
}
