package com.example.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.MessageDto;
import com.example.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PutMapping
  public void sendMessage(@RequestBody MessageDto messageDto){
    messageService.addMessage(messageDto);
  }
}
