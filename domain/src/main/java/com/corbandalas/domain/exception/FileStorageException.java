package com.corbandalas.domain.exception;

import java.io.IOException;

public class FileStorageException extends Exception{
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, IOException ex) {
        super(message, ex);
    }
}
