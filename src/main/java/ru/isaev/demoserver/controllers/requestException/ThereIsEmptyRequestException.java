package ru.isaev.demoserver.controllers.requestException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Зпрос не должен быть пустым")
public class ThereIsEmptyRequestException extends RuntimeException{ }
