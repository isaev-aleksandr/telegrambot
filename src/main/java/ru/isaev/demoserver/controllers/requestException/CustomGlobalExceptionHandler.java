package ru.isaev.demoserver.controllers.requestException;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

// дескриптор ошибок для @Valid
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status, WebRequest request) {

        Map body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

// Получить все ошибки
        List errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
// Обработка Exceptions не связанных с @Valid
    }
    @ExceptionHandler(ThereIsNoSuchBookException.class)
    protected ResponseEntity<CustomGlobalExceptionHandler.AwesomeException> handleThereIsNoSuchBookException() {
        return new ResponseEntity<>(new CustomGlobalExceptionHandler.AwesomeException("Нет книги с таким Id"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsUnacceptableValueException.class)
    protected ResponseEntity<CustomGlobalExceptionHandler.AwesomeException> handleThereIsUnacceptableValueException() {
        return new ResponseEntity<>(new CustomGlobalExceptionHandler.AwesomeException("Id должен быть больше 1"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsEmptyRequestException.class)
    protected ResponseEntity<CustomGlobalExceptionHandler.AwesomeException> handleThereIsEmptyRequestException() {
        return new ResponseEntity<>(new CustomGlobalExceptionHandler.AwesomeException("Зпрос не должен быть пустым"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsNotFoundBookException.class)
    protected ResponseEntity<CustomGlobalExceptionHandler.AwesomeException> handleThereIsNotFoundBookException() {
        return new ResponseEntity<>(new CustomGlobalExceptionHandler.AwesomeException("Нет книги с таким названием, жанром или автором"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    static class AwesomeException {
        private String message;
    }

}
