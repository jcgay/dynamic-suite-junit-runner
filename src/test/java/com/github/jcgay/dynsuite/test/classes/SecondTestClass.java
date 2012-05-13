package com.github.jcgay.dynsuite.test.classes;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jean-Christophe Gay
 */
public class SecondTestClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondTestClass.class);

    @Test
    public void fakeSecondTest() {
        LOGGER.debug("test executed.");
    }
}
