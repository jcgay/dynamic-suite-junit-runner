package com.github.jcgay.dynsuite.fakeTestClasses;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Jean-Christophe Gay
 */
@Ignore()
public class IgnoredTest {

    @Test
    public void should_not_be_ran() {
         fail();
    }
}
