package be.vdab.conferantie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DeelnemerIsAlBestaatException extends RuntimeException{
    public DeelnemerIsAlBestaatException(){
        super("Deelnemer bestaat al.");
    }
}
