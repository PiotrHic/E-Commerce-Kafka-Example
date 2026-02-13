package org.example.ecommercekafkaexample.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.ecommercekafkaexample.domain.OrderEvent;

import java.util.Map;

public class OrderEventDeserializer implements Deserializer<OrderEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderEvent deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, OrderEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing OrderEvent", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}