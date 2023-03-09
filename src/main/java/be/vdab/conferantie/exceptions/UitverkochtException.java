package be.vdab.conferantie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UitverkochtException extends RuntimeException {
    public UitverkochtException(){
        super("Alle tickets zijn uitverkocht");
    }
}
