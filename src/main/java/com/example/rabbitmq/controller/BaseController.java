package com.example.rabbitmq.controller;

import com.example.rabbitmq.direct.DirectSender;
import com.example.rabbitmq.fanout.FanoutSender;
import com.example.rabbitmq.hello.HelloSender;
import com.example.rabbitmq.topic.TopicSender;
import com.example.rabbitmq.user.User;
import com.example.rabbitmq.user.UserSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private UserSender userSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    /**
     * 发送单条
     */
    @GetMapping("hello")
    private void hello() {
        helloSender.send();
    }

    /**
     * 发送多条
     */
    @GetMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 10; i++) {
            helloSender.send("hellomsg:" + i);
        }
    }

    /**
     * 发送对象
     */
    @GetMapping("user")
    private void user() {

        User user = new User();
        user.setName("张三丰");
        user.setPass("1234");

        userSender.send(user);

    }

    @GetMapping("/directTest")
    public void directTest() {
        directSender.send();
    }


    /**
     * topic exchange类型rabbitmq测试
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }


    /**
     * fanout exchange类型rabbitmq测试
     */
    @RequestMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }


}
