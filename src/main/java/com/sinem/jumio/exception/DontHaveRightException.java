package com.sinem.jumio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Do not have right to do")
public class DontHaveRightException extends Exception
{

    static final long serialVersionUID = -3387516993334229948L;
    private String message;

    public DontHaveRightException(String message)
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