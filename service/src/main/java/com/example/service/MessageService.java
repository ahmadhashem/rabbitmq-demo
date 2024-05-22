package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Application;
import com.example.core.Message;
import com.example.core.MessageDto;

@Service
public class MessageService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

  private final RabbitTemplate rabbitTemplate;


  @Autowired
  public MessageService(RabbitTemplate rabbitTemplate){
    this.rabbitTemplate = rabbitTemplate;
  }

  public void addMessage(MessageDto messageDto){
    LOGGER.info("sending message {} to topic '{}'", messageDto, Application.TOPIC_EXCHANGE_NAME);
    String routingKey = "normal.message";
    if( messageDto.getId() > 10000) {
      routingKey = "upnormal.message";

    }
    rabbitTemplate.convertAndSend(Application.TOPIC_EXCHANGE_NAME, routingKey,
        Message.newBuilder()
        .setId(messageDto.getId())
        .setEmail(messageDto.getEmail())
        .build().toByteArray());
  }

}
