package com.example.PrimeNumbers.Repository;

import com.example.PrimeNumbers.Entity.PrimeNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrimeNumberRepository extends JpaRepository<PrimeNumber, Long> {
    PrimeNumber findByGuid(UUID guid);
}
