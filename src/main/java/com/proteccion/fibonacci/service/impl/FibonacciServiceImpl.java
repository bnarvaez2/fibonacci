package com.proteccion.fibonacci.service.impl;

import static com.proteccion.fibonacci.service.mapper.FibonacciMapper.FIBONACCI_MAPPER;

import com.proteccion.fibonacci.dto.FibonacciResponse;
import com.proteccion.fibonacci.repository.FibonacciRepository;
import com.proteccion.fibonacci.repository.model.FibonacciEntity;
import com.proteccion.fibonacci.service.EmailService;
import com.proteccion.fibonacci.service.FibonacciService;
import jakarta.transaction.Transactional;
import java.time.LocalTime;
import java.util.Collections;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciServiceImpl implements FibonacciService {

  private final EmailService emailSender;
  private final FibonacciRepository fibonacciRepository;

  public FibonacciServiceImpl(EmailService emailSender, FibonacciRepository fibonacciRepository) {
    this.emailSender = emailSender;
    this.fibonacciRepository = fibonacciRepository;
  }

  @Override
  @Transactional
    public List<Integer> generateFibonacci(String time) {
    LocalTime localTime = LocalTime.parse(time);
    int xSeed = localTime.getMinute() / 10;
    int ySeed = localTime.getMinute() % 10;
    int nNumbers = localTime.getSecond();

    List<Integer> fibonacciSeries = new ArrayList<>();
    fibonacciSeries.add(xSeed);
    fibonacciSeries.add(ySeed);

    for (int i = 2; i < nNumbers; i++) {
      fibonacciSeries.add(fibonacciSeries.get(i - 1) + fibonacciSeries.get(i - 2));
    }

    fibonacciSeries.sort(Collections.reverseOrder());
    FibonacciEntity entity = new FibonacciEntity();
    entity.setSerie(fibonacciSeries.toString());
    entity.setExecutionHour(time);
    fibonacciRepository.save(entity);

    emailSender.sendFibonacciMail(fibonacciSeries);

    return fibonacciSeries;
  }

  @Override
  public List<FibonacciResponse> getFibonacciSeries() {
    return FIBONACCI_MAPPER.toResponse(fibonacciRepository.findAll());
  }
}
