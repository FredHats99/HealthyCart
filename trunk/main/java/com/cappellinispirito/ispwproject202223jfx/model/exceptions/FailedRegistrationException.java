package com.cappellinispirito.ispwproject202223jfx.model.exceptions;

import java.io.Serial;

public class FailedRegistrationException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public FailedRegistrationException (String message){
        super(message);
    }

    public FailedRegistrationException (Throwable cause) {
        super(cause);
    }

    public FailedRegistrationException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
