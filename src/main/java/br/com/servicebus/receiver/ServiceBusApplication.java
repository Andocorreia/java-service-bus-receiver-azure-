package br.com.servicebus.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class ServiceBusApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBusApplication.class);
    private static final String QUEUE_NAME = "quservicebustreining";

    public static void main(String[] args) {
        SpringApplication.run(ServiceBusApplication.class, args);
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) {
        LOGGER.info("Sending message");
        jmsTemplate.convertAndSend(QUEUE_NAME, "Hello World");
    }

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveQueueMessage(String message) {
        LOGGER.info("Message received: {}", message);

    }
}