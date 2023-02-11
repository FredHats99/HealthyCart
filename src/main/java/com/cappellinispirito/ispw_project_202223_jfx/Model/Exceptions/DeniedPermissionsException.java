package com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions;

import java.io.Serial;

public class DeniedPermissionsException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DeniedPermissionsException (String message){
        super(message);
    }

    public DeniedPermissionsException (Throwable cause) {
        super(cause);
    }

    public DeniedPermissionsException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
