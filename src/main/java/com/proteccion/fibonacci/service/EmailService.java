package com.proteccion.fibonacci.service;

import java.util.List;

public interface EmailService {
  void sendFibonacciMail(List<Integer> fibonacciSeries);
}
