package ru.isaev.demoserver.controllers.requestException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Нет книги с таким Id")
public class ThereIsNoSuchBookException extends RuntimeException { }
