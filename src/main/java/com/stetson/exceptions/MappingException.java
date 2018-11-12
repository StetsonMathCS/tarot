package com.stetson.exceptions;

import java.io.IOException;

/** Exception used for RowMap.class for example, which tries to load data from Db into memory and
 * tries to map it.*/
public class MappingException extends IOException {
    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
    public MappingException(String message) {
        super(message);
    }
    public MappingException() {
        super();
    }
}
