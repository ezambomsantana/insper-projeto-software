package com.insper.partida.equipe.exception;

public class TeamAlreadyExistsException extends RuntimeException {

    public  TeamAlreadyExistsException() {
        super("Time já existe!");
    }

}
