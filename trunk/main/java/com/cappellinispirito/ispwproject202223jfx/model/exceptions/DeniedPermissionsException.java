package com.cappellinispirito.ispwproject202223jfx.model.exceptions;

import java.io.Serial;

public class DeniedPermissionsException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DeniedPermissionsException (String message){
        super(message);
    }

}
