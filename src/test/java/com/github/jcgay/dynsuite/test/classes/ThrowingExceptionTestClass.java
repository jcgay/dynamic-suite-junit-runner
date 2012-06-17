package com.github.jcgay.dynsuite.test.classes;

import org.junit.Test;

/**
 * @author Jean-Christophe Gay
 */
public class ThrowingExceptionTestClass {

    @Test
    public void throw_exception() {
        throw new RuntimeException();
    }
}
