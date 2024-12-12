package com.cubes4.CUBES4.annotation;

import java.lang.annotation.*;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {
}
