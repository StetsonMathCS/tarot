package com.stetson.exceptions;

/** Exception used for CPlexObj.class for example.*/
public class CPLEXException extends RuntimeException {
    public CPLEXException(String message, Throwable cause) {
        super(message, cause);
    }
    public CPLEXException(String message) {
        super(message);
    }
    public CPLEXException() {
        super();
    }
}
