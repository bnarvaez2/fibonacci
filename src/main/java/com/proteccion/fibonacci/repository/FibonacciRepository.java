package com.proteccion.fibonacci.repository;

import com.proteccion.fibonacci.repository.model.FibonacciEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FibonacciRepository extends JpaRepository<FibonacciEntity, UUID> {

}
