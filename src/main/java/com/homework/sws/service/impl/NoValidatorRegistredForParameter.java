package com.homework.sws.service.impl;

public class NoValidatorRegistredForParameter extends RuntimeException {

    public NoValidatorRegistredForParameter(String message) {
        super(message);
    }
}
