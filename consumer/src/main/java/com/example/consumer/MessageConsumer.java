package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.Application;
import com.example.core.Message;
import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class MessageConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

  @RabbitListener(queues = Application.QUEUE_NAME)
  public void onMessage(byte [] messageBytes) throws InvalidProtocolBufferException {
    Message message = Message.parseFrom(messageBytes);
    LOGGER.info("processing message : {}", message);
  }
}
