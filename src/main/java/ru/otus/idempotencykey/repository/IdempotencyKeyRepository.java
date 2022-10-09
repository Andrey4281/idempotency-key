package ru.otus.idempotencykey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.idempotencykey.domain.IdempotencyKey;

import java.util.UUID;

public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, UUID> {

    @Modifying
    @Query(value = "INSERT INTO idempotency_key(key) VALUES(:key)", nativeQuery = true)
    void insert(@Param("key") UUID key);
}
