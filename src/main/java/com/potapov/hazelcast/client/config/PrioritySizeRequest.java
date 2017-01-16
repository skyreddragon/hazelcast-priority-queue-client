package com.potapov.hazelcast.client.config;

import com.hazelcast.queue.impl.client.SizeRequest;

/**
 * Created by днс on 16.01.2017.
 */
public class PrioritySizeRequest extends SizeRequest {

    public PrioritySizeRequest() {
    }

    public PrioritySizeRequest(String name) {
        super(name);
    }

    @Override
    public int getFactoryId() {
        return PriorityPortableHook.F_ID;
    }

    @Override
    public String getServiceName() {
        return "priorityQueueService";
    }
}
