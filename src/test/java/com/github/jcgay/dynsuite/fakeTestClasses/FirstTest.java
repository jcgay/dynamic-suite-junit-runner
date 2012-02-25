package com.github.jcgay.dynsuite.fakeTestClasses;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Jean-Christophe Gay
 */
public class FirstTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstTest.class);
    
    @Test
    public void fakeFirstTest() {
        LOGGER.debug("test executed.");
    }
}
