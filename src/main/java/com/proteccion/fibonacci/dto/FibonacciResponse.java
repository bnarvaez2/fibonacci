package com.proteccion.fibonacci.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciResponse {
  private String serie;
  private String executionHour;
}
