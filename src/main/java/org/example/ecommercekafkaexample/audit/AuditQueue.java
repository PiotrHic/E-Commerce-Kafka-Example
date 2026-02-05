package org.example.ecommercekafkaexample.audit;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class AuditQueue {

    private static final int MAX_QUEUE_SIZE = 10_000;

    private final BlockingQueue<AuditEventEntity> queue =
            new ArrayBlockingQueue<> (MAX_QUEUE_SIZE);

    public boolean offer(AuditEventEntity event) {
        return queue.offer(event);
    }

    public AuditEventEntity take() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }
}
