package com.lacronicus.mocktopus.core.mocktopus.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fdoyle on 7/10/14.
 * dont show defaults as options, just ones that are added by additional annotations
 * //todo implement
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NoDefaults {

}

