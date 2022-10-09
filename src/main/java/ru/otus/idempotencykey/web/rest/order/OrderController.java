package ru.otus.idempotencykey.web.rest.order;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.idempotencykey.dto.OrderDto;
import ru.otus.idempotencykey.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "orders/create")
    public ResponseEntity<Void> insert(@RequestBody OrderDto orderDto,
                                       @RequestHeader("Idempotency-Key") UUID idempotencyKey) {
        orderService.insertOrder(orderDto, idempotencyKey);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
