package ru.otus.idempotencykey.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "idempotency_key")
public class IdempotencyKey {

    @Id
    @Column(name="key", nullable = false)
    private UUID key;
}
