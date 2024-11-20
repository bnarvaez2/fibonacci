package com.proteccion.fibonacci.config;

import static com.proteccion.fibonacci.util.Constants.INTERNAL_ERROR;
import static com.proteccion.fibonacci.util.Constants.PARAMS_ERROR;
import static com.proteccion.fibonacci.util.Constants.VALIDATION_ERROR;
import static com.proteccion.fibonacci.util.ResponseUtil.response;
import static java.util.Objects.requireNonNull;

import com.proteccion.fibonacci.dto.GenericResponse;
import com.proteccion.fibonacci.exception.BadRequestException;
import com.proteccion.fibonacci.exception.InternalServerErrorException;
import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerConfig {

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<GenericResponse> validationException(WebExchangeBindException e) {
    return response(VALIDATION_ERROR, getMessage(e.getAllErrors()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericResponse> validationException(MethodArgumentNotValidException ex) {
    return response(VALIDATION_ERROR, getMessage(ex.getAllErrors()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<GenericResponse> validationException(BadRequestException ex) {
    return response(VALIDATION_ERROR, ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<GenericResponse> validationException(InternalServerErrorException ex) {
    return response(INTERNAL_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<GenericResponse> validationException(HandlerMethodValidationException ex) {
    return response(PARAMS_ERROR, getMessage(ex.getDetailMessageArguments()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<GenericResponse> validationException(ConstraintViolationException ex) {
    return response(PARAMS_ERROR, ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  private String getMessage(List<ObjectError> errors) {
    return errors.stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.joining(", "));
  }

  private String getMessage(Object[] errors) {
    return Arrays.stream(errors)
      .map(Object::toString)
      .collect(Collectors.joining(", "));
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<GenericResponse> responseStatusException(ResponseStatusException e) {
    return response(requireNonNull(HttpStatus.resolve(e.getStatusCode().value())).getReasonPhrase().toUpperCase(),
      e.getReason(),
      HttpStatus.valueOf(e.getStatusCode().value()));
  }
}
