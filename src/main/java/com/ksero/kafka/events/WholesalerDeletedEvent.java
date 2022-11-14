package com.ksero.kafka.events;

import com.ksero.kafka.entity.WholesalerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WholesalerDeletedEvent extends Event<WholesalerDto> {
}
