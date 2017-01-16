package com.potapov.hazelcast.client;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by днс on 16.01.2017.
 */
@ContextConfiguration({"classpath:spring-hazelcast.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QueueTester {

    IQueue<Object> queue;

    @Autowired
    HazelcastInstance client;

    @Before
    public void init() {
        queue = client.getDistributedObject("priorityQueueService", "foo");
    }

    @After
    public void destroy() {
        queue.clear();
    }

    @Test
    public void testPoll() {
        queue.offer("b");
        queue.offer("a");
        assertEquals(queue.poll(), "a");
    }

    @Test
    public void testOffer() {
        queue.offer("c");
        assertEquals(queue.size(), 1);
    }

}
