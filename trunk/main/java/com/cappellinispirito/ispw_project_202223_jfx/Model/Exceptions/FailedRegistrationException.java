package com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions;

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
