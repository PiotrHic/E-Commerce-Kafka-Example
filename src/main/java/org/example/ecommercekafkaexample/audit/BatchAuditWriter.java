package org.example.ecommercekafkaexample.audit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BatchAuditWriter {

    private static final int BATCH_SIZE = 500;

    private final AuditQueue auditQueue;
    private final AuditEventRepository repository;

    public BatchAuditWriter(AuditQueue auditQueue,
                            AuditEventRepository repository) {
        this.auditQueue = auditQueue;
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void flushBatch() {
        List<AuditEventEntity> batch = new ArrayList<>(BATCH_SIZE);

        auditQueue.queue.drainTo(batch, BATCH_SIZE);

        if (batch.isEmpty()) {
            return;
        }

        repository.saveAll(batch);
    }
}