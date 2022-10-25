package com.ksero.security.events;

import com.ksero.kafka.events.Event;
import com.ksero.security.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDeletedEvent extends Event<User> {
}
