package br.com.servicebus.receiver.rest;

import br.com.servicebus.receiver.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
public class QueueController implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private static final String QUEUE_NAME = "quservicebustreining";

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void run(String... args) {
		LOGGER.info("Sending message");
		jmsTemplate.convertAndSend(QUEUE_NAME, "Hello World");
	}

	@JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(String message) {
		LOGGER.info("Message received: {}", message);

	}
}
