package com.proteccion.fibonacci.service.mapper;

import com.proteccion.fibonacci.dto.FibonacciResponse;
import com.proteccion.fibonacci.repository.model.FibonacciEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-20T12:02:29-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class FibonacciMapperImpl implements FibonacciMapper {

    @Override
    public List<FibonacciResponse> toResponse(List<FibonacciEntity> series) {
        if ( series == null ) {
            return null;
        }

        List<FibonacciResponse> list = new ArrayList<FibonacciResponse>( series.size() );
        for ( FibonacciEntity fibonacciEntity : series ) {
            list.add( fibonacciEntityToFibonacciResponse( fibonacciEntity ) );
        }

        return list;
    }

    protected FibonacciResponse fibonacciEntityToFibonacciResponse(FibonacciEntity fibonacciEntity) {
        if ( fibonacciEntity == null ) {
            return null;
        }

        FibonacciResponse.FibonacciResponseBuilder fibonacciResponse = FibonacciResponse.builder();

        fibonacciResponse.serie( fibonacciEntity.getSerie() );
        fibonacciResponse.executionHour( fibonacciEntity.getExecutionHour() );

        return fibonacciResponse.build();
    }
}
