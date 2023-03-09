package be.vdab.conferantie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SessieNietGevondenException extends RuntimeException {
    public SessieNietGevondenException(long id) {
        super("Sessie niet gevonden. ID: " + id);
    }
}
