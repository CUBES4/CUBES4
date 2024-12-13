package com.cubes4.CUBES4.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FXMLController {
}
