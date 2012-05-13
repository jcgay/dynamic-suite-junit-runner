package com.github.jcgay.dynsuite.test.classes;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Jean-Christophe Gay
 */
@Ignore()
public class IgnoredTestClass {

    @Test
    public void should_not_be_ran() {
         fail();
    }
}
