package com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions;

import java.io.Serial;

public class FailedQueryToOpenFoodFacts extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public FailedQueryToOpenFoodFacts (String message){
        super(message);
    }

    public FailedQueryToOpenFoodFacts (Throwable cause) {
        super(cause);
    }

    public FailedQueryToOpenFoodFacts (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
