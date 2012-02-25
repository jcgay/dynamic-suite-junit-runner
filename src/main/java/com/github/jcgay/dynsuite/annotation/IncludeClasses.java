package com.github.jcgay.dynsuite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describe which method to run to define test(s) at runtime with {@link com.github.jcgay.dynsuite.runner.DynamicSuiteRunner}. <br />
 * A candidate method to this annotation should be public, returning a {@link java.util.List} of {@link Class} and taking no argument.
 *
 * @author Jean-Christophe Gay
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IncludeClasses {
}
