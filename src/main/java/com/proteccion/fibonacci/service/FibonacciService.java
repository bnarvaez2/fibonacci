package com.proteccion.fibonacci.service;

import com.proteccion.fibonacci.dto.FibonacciResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface FibonacciService {

  List<Integer> generateFibonacci(String time);

  List<FibonacciResponse> getFibonacciSeries();
}
