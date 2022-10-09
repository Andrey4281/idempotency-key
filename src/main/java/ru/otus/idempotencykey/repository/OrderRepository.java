package ru.otus.idempotencykey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.idempotencykey.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
