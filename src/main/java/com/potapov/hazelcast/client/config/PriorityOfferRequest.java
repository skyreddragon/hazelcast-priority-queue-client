package com.potapov.hazelcast.client.config;

import com.hazelcast.nio.serialization.Data;
import com.hazelcast.queue.impl.client.OfferRequest;

/**
 * Created by днс on 16.01.2017.
 */
public class PriorityOfferRequest extends OfferRequest {

    public PriorityOfferRequest() {
    }

    public PriorityOfferRequest(String name, Data data) {
        super(name, data);
    }

    public PriorityOfferRequest(String name, long timeoutMillis, Data data) {
        super(name, timeoutMillis, data);
    }

    public int getFactoryId() {
        return PriorityPortableHook.F_ID;
    }

    @Override
    public String getServiceName() {
        return "priorityQueueService";
    }
}
