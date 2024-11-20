package com.proteccion.fibonacci.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "fibonacci")
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciEntity {

  @Id
  @GeneratedValue
  private UUID id;
  private String serie;
  private String executionHour;
}
