package ru.nabokovsg.lab_nk.exceptions;
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
