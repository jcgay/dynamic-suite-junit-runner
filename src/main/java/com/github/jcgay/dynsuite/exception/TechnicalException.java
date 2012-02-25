package com.github.jcgay.dynsuite.exception;

/**
 * Indicate that a runtime error occurs.
 *
 * @author Jean-Christophe Gay
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException() {
    }

    public TechnicalException(String s) {
        super(s);
    }

    public TechnicalException(String s, Throwable cause) {
        super(s, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }
}

