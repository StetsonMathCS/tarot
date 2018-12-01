/*
 * Copyright (c) 2018.
 *
 * Kevin A. Riedl (WSDT)
 * Github: https://github.com/wsdt
 * LinkedIn: https://linkedin.com/in/kevin-riedl-947219158/
 */

package com.stetson.exceptions;

/** Exception used for SessionController.class for example.*/
public class HashException extends RuntimeException {
    public HashException(String message, Throwable cause) {
        super(message, cause);
    }
    public HashException(String message) {
        super(message);
    }
    public HashException() {
        super();
    }
}
