package be.vdab.conferantie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SprekerNietGevondenException extends RuntimeException {
    public SprekerNietGevondenException(long id) {
        super("Spreker niet gevonden. ID: " + id);
    }
}
