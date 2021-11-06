package edu.co.icesi.model;

public class Game {

    private Board board;
    private int numPlayers;
    private Node firstPlayer;

    public void createPlayers(int i, Node prev){
        if(i<numPlayers){
            char character = (char)(i+33);
            int numPlayer = i;

            Node currentPlayer = new Node(new Player(character, numPlayer));

            prev.setNext(currentPlayer);
            currentPlayer.setPrev(prev);

            createPlayers(i+1, currentPlayer);
        }
    }

    public void createFirstPlayer(){
        char character = (char)33;
        int numPlayer = 0;

        firstPlayer = new Node(new Player(character, numPlayer));
    }

    public int rollDice(){
        return (int)(Math.random()*6)+1;
    }


}
