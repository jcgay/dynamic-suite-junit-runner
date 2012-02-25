package com.github.jcgay.dynsuite.exception;

/**
 * Indicate that something is wrong in the configuration of the runner.
 *
 * @author Jean-Christophe Gay
 */
public class IllegalConfigurationException extends TechnicalException {

    public IllegalConfigurationException() {
    }

    public IllegalConfigurationException(String s) {
        super(s);
    }

    public IllegalConfigurationException(String s, Throwable cause) {
        super(s, cause);
    }

    public IllegalConfigurationException(Throwable cause) {
        super(cause);
    }
}
