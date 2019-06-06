package com.lambdaschool.tiemendo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class IllegalDeleteException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public IllegalDeleteException(String message)
    {
        super(message);
    }

    public IllegalDeleteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}