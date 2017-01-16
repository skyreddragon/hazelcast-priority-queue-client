package com.potapov.hazelcast.client.config;

import com.hazelcast.client.impl.client.ClientRequest;
import com.hazelcast.client.spi.ClientProxy;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemListener;
import com.hazelcast.monitor.LocalQueueStats;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.queue.impl.client.SizeRequest;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class ClientPriorityQueueProxyImpl<E> extends ClientProxy implements IQueue<E> {

    public ClientPriorityQueueProxyImpl(String serviceName, String objectName) {
        super(serviceName, objectName);
    }

    public boolean offer(E e) {
        try {
            return offer(e, 0, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            return false;
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        Data data = getContext().getSerializationService().toData(e);
        PriorityOfferRequest request = new PriorityOfferRequest(getName(), unit.toMillis(timeout), data);
        return invokeInterruptibly(request);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public void put(E e) throws InterruptedException {

    }

    @Override
    public E take() throws InterruptedException {
        return null;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        PriorityPollRequest request = new PriorityPollRequest(getName());
        return invokeInterruptibly(request);
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        try {
            return poll(0, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int size() {
        try {
            PrioritySizeRequest request = new PrioritySizeRequest(getName());
            return invokeInterruptibly(request);
        } catch (InterruptedException e) {
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        PriorityClearRequest request = new PriorityClearRequest(getName());
        try {
            invokeInterruptibly(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LocalQueueStats getLocalQueueStats() {
        return null;
    }

    @Override
    public String addItemListener(ItemListener<E> listener, boolean includeValue) {
        return null;
    }

    @Override
    public boolean removeItemListener(String registrationId) {
        return false;
    }

    protected <T> T invokeInterruptibly(ClientRequest req) throws InterruptedException {
        return super.invokeInterruptibly(req, getPartitionKey());
    }

}
