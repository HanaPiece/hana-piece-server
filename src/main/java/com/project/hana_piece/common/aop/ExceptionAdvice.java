package com.project.hana_piece.common.aop;

import com.project.hana_piece.common.exception.JsonElementNotFoundException;
import com.project.hana_piece.common.exception.NetworkIOException;
import com.project.hana_piece.common.exception.ValueInvalidException;
import com.project.hana_piece.common.dto.ErrorResponse;
import com.project.hana_piece.common.exception.AccessDeniedException;
import com.project.hana_piece.common.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * [EntityNotFoundException] 발생 시 404 NOT FOUND 응답
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandler(
        EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.NOT_FOUND);
    }

    /**
     * [ValueInvalidException] 발생 시 400 BAD_REQUEST 응답
     */
    @ExceptionHandler(ValueInvalidException.class)
    public ResponseEntity<ErrorResponse> valueInvalidExceptionHandler(
        ValueInvalidException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    /**
     * [AccessDeniedException] 발생 시 403 FORBIDDEN 응답
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedExceptionHandler(
        AccessDeniedException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.FORBIDDEN);
    }

    /**
     * [NetworkIOException] 발생 시 503 SERVICE_UNAVAILABLE 응답
     */
    @ExceptionHandler(NetworkIOException.class)
    public ResponseEntity<ErrorResponse> networkIOExceptionExceptionHandler(
        NetworkIOException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * [JsonElementNotFoundException] 발생 시 500 INTERNAL_SERVER_ERROR 응답
     */
    @ExceptionHandler(JsonElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> JsonElementNotFoundExceptionHandler(
        JsonElementNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

