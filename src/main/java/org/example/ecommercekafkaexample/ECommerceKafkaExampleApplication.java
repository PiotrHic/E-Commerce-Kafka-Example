package org.example.ecommercekafkaexample;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class ECommerceKafkaExampleApplication {

    public static void main ( String[] args ) {
        System.out.println("🔥🔥🔥 MAIN STARTED 🔥🔥🔥");
        SpringApplication.run ( ECommerceKafkaExampleApplication.class, args );
    }

    @PostConstruct
    public void logKafkaConfig() {
        System.out.println("ENV SPRING_KAFKA_BOOTSTRAP_SERVERS = "
                + System.getenv("SPRING_KAFKA_BOOTSTRAP_SERVERS"));
    }

    @Autowired
    private Environment environment;

    @PostConstruct
    public void logKafkaConfig1() {
        System.out.println("SPRING sees bootstrap-servers = "
                + environment.getProperty("spring.kafka.bootstrap-servers"));
    }

}
