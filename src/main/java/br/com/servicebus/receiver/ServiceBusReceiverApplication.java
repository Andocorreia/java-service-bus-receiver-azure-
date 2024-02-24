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
public class ServiceBusReceiverApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBusReceiverApplication.class);
    private static final String QUEUE_NAME = "quservicebustreining";
    private static final String TOPIC_NAME = "tpservicebustreining";

    private static final String SUBSCRIPTION_NAME = "ssservicebustreining";

    public static void main(String[] args) {
        SpringApplication.run(ServiceBusReceiverApplication.class, args);
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) {
        LOGGER.info("Sending message");
        jmsTemplate.convertAndSend(QUEUE_NAME, "Start JOB Queue");
        jmsTemplate.convertAndSend(TOPIC_NAME, "Start JOB Tipoc");
    }

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveQueueMessage(String message) {
        LOGGER.info("Queue Message received: {}", message);

    }

    @JmsListener(destination = TOPIC_NAME, containerFactory = "topicJmsListenerContainerFactory",
            subscription = SUBSCRIPTION_NAME)
    public void receiveTopicMessage(String message) {
        LOGGER.info("Topic Message received: {}", message);
    }
}