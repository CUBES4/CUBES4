package com.cubes4.CUBES4.exceptions;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
