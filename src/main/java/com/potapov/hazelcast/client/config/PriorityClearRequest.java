package com.potapov.hazelcast.client.config;

import com.hazelcast.queue.impl.client.ClearRequest;

/**
 * Created by днс on 16.01.2017.
 */
public class PriorityClearRequest extends ClearRequest {

    public PriorityClearRequest() {
    }

    public PriorityClearRequest(String name) {
        super(name);
    }

    @Override
    public int getFactoryId() {
        return 82;
    }

    @Override
    public String getServiceName() {
        return "priorityQueueService";
    }
}
