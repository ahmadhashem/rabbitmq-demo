package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static final String QUEUE_NAME = "messagesQueue";
	public static final String QUEUE_ADDITIONAL_PROCESSING_NAME = "messagesAdditionalProcessingQueue";
	private static final String TOPIC_EXCHANGE_NAME = "messagesExchange";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(QUEUE_NAME)
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean(QUEUE_ADDITIONAL_PROCESSING_NAME)
	Queue additionalProcessingQueue() {
		return new Queue(QUEUE_ADDITIONAL_PROCESSING_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding messagesQueueBinding(@Qualifier(QUEUE_NAME) Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("#.message.#");
	}

	@Bean
	Binding additionalProcessingQueueBinding(@Qualifier(QUEUE_ADDITIONAL_PROCESSING_NAME) Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("#.upnormal.#");
	}

}
