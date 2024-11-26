package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMqService {

  @Value("${rabbitmq.exchange.name}")
  private String exchangeName;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  private final RabbitTemplate rabbitTemplate;

   // Queue로 메시지를 발행
   // @param messageDto 발행할 메시지의 DTO 객체
  public void sendMessage(MessageDto messageDto) {
    log.info("message sent: {}", messageDto.toString());
    rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto);
  }
}