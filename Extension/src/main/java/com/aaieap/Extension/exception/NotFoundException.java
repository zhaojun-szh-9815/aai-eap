package com.aaieap.Extension.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String obj, String field, String value) {
        super(obj + " with " + field + ": " + value + " not found!");
    }
}