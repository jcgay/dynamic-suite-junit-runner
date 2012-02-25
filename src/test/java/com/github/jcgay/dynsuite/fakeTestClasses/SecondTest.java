package com.github.jcgay.dynsuite.fakeTestClasses;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jean-Christophe Gay
 */
public class SecondTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondTest.class);

    @Test
    public void fakeSecondTest() {
        LOGGER.debug("test executed.");
    }
}
