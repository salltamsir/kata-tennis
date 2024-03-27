package org.kata.tennis;

public class Player {

    private int points = 0;
    private String name;
    public Player(String name){
        this.name = name;
    }
    public void doScore(){
        ++points;
    }
    public int getPoints() {
        return points;
    }

    public String toString(){
        return "Player "+this.name;
    }
}
