package com.proteccion.fibonacci.service;

import com.proteccion.fibonacci.dto.FibonacciResponse;
import com.proteccion.fibonacci.exception.BadRequestException;
import com.proteccion.fibonacci.exception.InternalServerErrorException;
import com.proteccion.fibonacci.repository.FibonacciRepository;
import com.proteccion.fibonacci.repository.model.FibonacciEntity;
import com.proteccion.fibonacci.service.impl.FibonacciServiceImpl;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {

  @Mock
  private EmailService emailSender;

  @Mock
  private FibonacciRepository fibonacciRepository;

  @InjectMocks
  private FibonacciServiceImpl fibonacciService;

  @BeforeEach
  void init() {
    fibonacciRepository.deleteAll();
  }

  @Test
  public void verifyService() {
    assertNotNull(fibonacciService);
  }

  @Test
  public void testGenerateFibonacci() {
    String time = "12:23:06";

    fibonacciService.generateFibonacci(time);

    verify(emailSender, times(1)).sendFibonacciMail(any());
    verify(fibonacciRepository, times(1)).save(any(FibonacciEntity.class));
  }
  @Test
  public void testGenerateFibonacciThrowBadRequest() {
    String time = "12:23:-06";

    assertThrows(BadRequestException.class, () -> fibonacciService.generateFibonacci(time));
  }
  @Test
  public void testGenerateFibonacciThrowInternalError() {
    String time = "12:23:06";

    fibonacciService.generateFibonacci(time);
    when(fibonacciRepository.save(any())).thenThrow(new RuntimeException("Error on save in database"));

    assertThrows(InternalServerErrorException.class, () -> fibonacciService.generateFibonacci(time));
  }

  @Test
  public void testGetFibonacciSeriesReturnEmptyList() {
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    fibonacciService.generateFibonacci(now.format(formatter));

    val series = fibonacciService.getFibonacciSeries();

    assertTrue(series.isEmpty());
  }

  @Test
  public void testGetFibonacciSeriesReturnList() {
    FibonacciEntity entity1 = new FibonacciEntity();
    entity1.setSerie("3, 2, 1, 1, 0");
    entity1.setExecutionHour(LocalTime.now().toString());

    List<FibonacciEntity> entities = singletonList(entity1);

    FibonacciResponse response1 = new FibonacciResponse("3, 2, 1, 1, 0",
      entity1.getExecutionHour());

    List<FibonacciResponse> responses = singletonList(response1);

    when(fibonacciRepository.findAll()).thenReturn(entities);

    List<FibonacciResponse> result = fibonacciService.getFibonacciSeries();

    assertEquals(responses, result);
  }
}

