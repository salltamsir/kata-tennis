package org.kata.tennis;

public enum Status {

    WIN("wins the game"),
    DEUCE("Deuce"),
    AD("Advantage");

    private final String value;

    Status(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
