package com.proteccion.fibonacci.service.mapper;


import com.proteccion.fibonacci.dto.FibonacciResponse;
import com.proteccion.fibonacci.repository.model.FibonacciEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FibonacciMapper {

  FibonacciMapper FIBONACCI_MAPPER = Mappers.getMapper(FibonacciMapper.class);

  List<FibonacciResponse> toResponse(List<FibonacciEntity> series);

}
