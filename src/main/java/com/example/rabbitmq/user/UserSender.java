package com.example.rabbitmq.user;

import com.example.rabbitmq.application.Application;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {

        System.out.println("user send : " + user.getName() + "/" + user.getPass());

        this.rabbitTemplate.convertAndSend(Application.USER_QUEUE_NAME, user);

    }
}
