package ru.otus.idempotencykey.mapper;

import org.springframework.stereotype.Component;
import ru.otus.idempotencykey.domain.Order;
import ru.otus.idempotencykey.dto.OrderDto;

@Component
public class OrderRowMapper {

    public Order toOrderKey(OrderDto orderDto) {
        return Order.builder()
                .orderName(orderDto.getOrderName())
                .build();
    }

    //
    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .build();
    }
}
