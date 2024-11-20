package com.proteccion.fibonacci.service;

import com.proteccion.fibonacci.config.MailConfig;
import com.proteccion.fibonacci.exception.InternalServerErrorException;
import com.proteccion.fibonacci.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

  @Mock
  private JavaMailSender emailSender;

  @Mock
  private MailConfig mailConfig;

  @InjectMocks
  private EmailServiceImpl emailService;

  @Test
  public void testSendFibonacciMail() {
    when(mailConfig.getTo()).thenReturn("test@example.com");
    List<Integer> fibonacciSeries = Arrays.asList(0, 1, 1, 2, 3, 5);

    emailService.sendFibonacciMail(fibonacciSeries);

    verify(emailSender).send(any(SimpleMailMessage.class));
  }
  @Test
  public void testSendFibonacciMailThrowError() {
    when(mailConfig.getTo()).thenThrow(new RuntimeException("Generic error"));
    List<Integer> fibonacciSeries = Arrays.asList(0, 1, 1, 2, 3, 5);

    assertThrows(InternalServerErrorException.class, () -> emailService.sendFibonacciMail(fibonacciSeries));
  }
}
