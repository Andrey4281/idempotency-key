package ru.otus.idempotencykey.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.idempotencykey.domain.Order;
import ru.otus.idempotencykey.dto.OrderDto;
import ru.otus.idempotencykey.mapper.OrderRowMapper;
import ru.otus.idempotencykey.repository.IdempotencyKeyRepository;
import ru.otus.idempotencykey.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRowMapper orderRowMapper;
    private final OrderRepository orderRepository;
    private final IdempotencyKeyRepository idempotencyKeyRepository;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertOrder(OrderDto orderDto, UUID key) {
        Order order = orderRowMapper.toOrderKey(orderDto);
        orderRepository.save(order);
        idempotencyKeyRepository.insert(key);
    }

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(order ->
                OrderDto.builder().orderName(order.getOrderName()).build()).collect(Collectors.toList());
    }
}
