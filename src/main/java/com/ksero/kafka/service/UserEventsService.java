package com.ksero.kafka.service;

import com.ksero.kafka.events.Event;
import com.ksero.kafka.events.EventType;
import com.ksero.products.domain.service.ProductService;
import com.ksero.security.events.UserDeletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventsService {

    @Autowired
    private ProductService service;

    @KafkaListener(
            topics = "${topic.user.name:users}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(UserDeletedEvent.class)) {
            UserDeletedEvent userDeletedEvent = (UserDeletedEvent) event;
            log.info("Received Customer deleted event .... with Id={}, data={}",
                    userDeletedEvent.getId(),
                    userDeletedEvent.getData().toString());

            if(userDeletedEvent.getType() == EventType.DELETED){
                service.deleteByUserId(userDeletedEvent.getData().getId());
            }
        }
    }
}
