package com.proteccion.fibonacci.controller;

import static com.proteccion.fibonacci.util.ResponseUtil.response;

import com.proteccion.fibonacci.dto.FibonacciResponse;
import com.proteccion.fibonacci.dto.GenericResponse;
import com.proteccion.fibonacci.service.FibonacciService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/fibonacci")
@RequiredArgsConstructor
@Tag(name = "Fibonacci Controller")
public class FibonacciController {

  private final FibonacciService fibonacciService;

  @PostMapping("/generate")
  @Operation(summary = "Genera la serie Fibonacci", description = "Devuelve una lista con la serie Fibonacci generada")
  public ResponseEntity<GenericResponse> generateFibonacci(
    @NotNull(message = "time is missing")
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "time is not valid")
    @RequestParam String time) {
    val fibonacciSerie = fibonacciService.generateFibonacci(time);

    return response("Fibonacci series was successfully generated" , fibonacciSerie.toString(), HttpStatus.OK);
  }

  @PostMapping()
  @Operation(summary = "Genera la serie Fibonacci", description = "Devuelve una lista con la serie Fibonacci generada")
  public ResponseEntity<GenericResponse> generateFibonacci() {
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    val fibonacciSerie = fibonacciService.generateFibonacci(now.format(formatter));

    return response("Fibonacci series was successfully generated" , fibonacciSerie.toString(), HttpStatus.OK);
  }

  @GetMapping()
  @Operation(summary = "Genera la serie Fibonacci", description = "Devuelve una lista con todas las series de Fibonacci generada")
  public List<FibonacciResponse> getFibonacciSeries() {
    return fibonacciService.getFibonacciSeries();
  }
}

