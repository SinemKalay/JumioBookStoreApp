package com.sinem.jumio.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice
{
    @ExceptionHandler({ConstraintsViolationException.class,


    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(
        final ConstraintsViolationException exception,
        final HttpServletRequest request)
    {

        return generateError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURI());

    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleEntityNotFoundException(
        final EntityNotFoundException exception,
        final HttpServletRequest request)
    {

        return generateError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());

    }


    @ExceptionHandler(DontHaveRightException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ExceptionResponse handleDontHaveRightException(
        final DontHaveRightException exception,
        final HttpServletRequest request)
    {

        return generateError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), request.getRequestURI());

    }


    @ExceptionHandler(InsufficientStorageException.class)
    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    public @ResponseBody
    ExceptionResponse handleInsufficientStorageException(
        final InsufficientStorageException exception,
        final HttpServletRequest request)
    {

        return generateError(HttpStatus.INSUFFICIENT_STORAGE.value(), exception.getMessage(), request.getRequestURI());

    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(
        final Exception exception,
        final HttpServletRequest request)
    {
        return generateError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURI());

    }


    private ExceptionResponse generateError(int httpStatusValue, String errorMessage, String requestedURI)
    {
        ExceptionResponse error = new ExceptionResponse();
        error.setHttpStatus(httpStatusValue);
        error.setErrorMessage(errorMessage);
        error.callerURL(requestedURI);

        return error;
    }
}
