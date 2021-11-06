package edu.co.icesi.model;

public class Player {

    private char character;
    private int numberPlayer;
    private Player next;

    public Player(char character, int numberPlayer) {
        this.character = character;
        this.numberPlayer = numberPlayer;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }
}
