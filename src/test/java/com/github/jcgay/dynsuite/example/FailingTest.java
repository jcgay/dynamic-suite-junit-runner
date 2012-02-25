package com.github.jcgay.dynsuite.example;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Jean-Christophe Gay
 */
public class FailingTest {
    
    @Test
    public void failing_test() {
         fail("This test should fail.");
    }
    
}
