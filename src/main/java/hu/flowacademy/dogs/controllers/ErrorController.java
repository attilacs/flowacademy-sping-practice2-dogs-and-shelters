package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.exceptions.AddressAlreadyExistsException;
import hu.flowacademy.dogs.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel notValidArgument() {
        return new ErrorModel("Érvénytelen bemenet!");
    }

    @ExceptionHandler({AddressAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel addressAlreadyExists() {
        return new ErrorModel("A megadott cím már létezik");
    }
}