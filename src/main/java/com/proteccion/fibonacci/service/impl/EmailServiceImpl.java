package com.proteccion.fibonacci.service.impl;
import com.proteccion.fibonacci.config.MailConfig;
import com.proteccion.fibonacci.service.EmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements com.proteccion.fibonacci.service.EmailService {

  private final JavaMailSender emailSender;
  private final MailConfig mailConfig;

  public EmailServiceImpl(JavaMailSender emailSender, MailConfig mailConfig) {
    this.emailSender = emailSender;
    this.mailConfig = mailConfig;
  }

  public void sendFibonacciMail(List<Integer> fibonacciSeries) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mailConfig.getTo().split(","));
    message.setSubject("Serie Fibonacci");
    message.setText("La serie Fibonacci generada es: " + fibonacciSeries);
    emailSender.send(message);
  }
}


