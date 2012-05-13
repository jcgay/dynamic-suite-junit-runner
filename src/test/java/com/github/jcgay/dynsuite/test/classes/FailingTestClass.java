package com.github.jcgay.dynsuite.test.classes;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Jean-Christophe Gay
 */
public class FailingTestClass {
    
    @Test
    public void failing_test() {
         fail("This test should fail.");
    }
    
}
