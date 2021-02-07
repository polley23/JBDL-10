package com.gfg.majorprojectjbdl10.notification.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorprojectjbdl10.entity.UserEntity;
import com.gfg.majorprojectjbdl10.models.Notification;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.InternetAddress;

@Service
public class NotificationImpl implements NotificationService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    SimpleMailMessage simpleMailMessage;
    @Autowired
    RestTemplate restTemplate;

    ObjectMapper objectMapper =  new ObjectMapper();
    @SneakyThrows
    @Override
    @KafkaListener(topics = "notification" , groupId = "notification-service")
    public void notify(String notification) {
        Notification request = objectMapper.readValue(notification,Notification.class);
        UserEntity userEntity = restTemplate.getForEntity("http://localhost:8080/user/" + request.getUser(),
                UserEntity.class).getBody();
        InternetAddress address = new InternetAddress(userEntity.getEmail(),"","UTF-8");

        simpleMailMessage.setFrom("geekstutorialemail2020@gmail.com");
        simpleMailMessage.setTo(address.getAddress());
        simpleMailMessage.setText(request.getMessage());
        simpleMailMessage.setSubject(request.getSubject());

        javaMailSender.send(simpleMailMessage);

    }
}
