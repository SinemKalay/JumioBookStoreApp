package com.sinem.jumio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE, reason = "The book requested is not in the stock right now!")
public class InsufficientStorageException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;
    private String message;


    public InsufficientStorageException(String message)
    {
        super(message);
        this.message = message;

    }


    @Override
    public String getMessage()
    {
        return message;
    }
}
