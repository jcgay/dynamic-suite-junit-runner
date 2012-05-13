package com.github.jcgay.dynsuite.test.classes;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Jean-Christophe Gay
 */
public class FirstTestClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstTestClass.class);
    
    @Test
    public void fakeFirstTest() {
        LOGGER.debug("test executed.");
    }
}
