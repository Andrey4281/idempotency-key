package ru.otus.idempotencykey.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderName;
}
