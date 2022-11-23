package com.ksero.kafka.service;

import com.ksero.kafka.events.Event;
import com.ksero.kafka.events.EventType;
import com.ksero.products.domain.service.ProductService;
import com.ksero.kafka.events.WholesalerDeletedEvent;
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
            topics = "${topic.user.name:wholesalers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "products")
    public void wholesaler(Event<?> event) {
        if (event.getClass().isAssignableFrom(WholesalerDeletedEvent.class)) {
            WholesalerDeletedEvent wholesalerDeletedEvent = (WholesalerDeletedEvent) event;
            log.info("Received wholesaler deleted event .... with Id={}, data={}",
                    wholesalerDeletedEvent.getId(),
                    wholesalerDeletedEvent.getData().getUsername());

            if(wholesalerDeletedEvent.getType() == EventType.DELETED){
                service.deleteByUserId(wholesalerDeletedEvent.getData().getId());
            }
        }
    }
}
