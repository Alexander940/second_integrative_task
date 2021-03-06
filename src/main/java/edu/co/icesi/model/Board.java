package edu.co.icesi.model;

import java.util.Random;

/**
 * This class is a Board
 * @author Alexander Echeverry
 * @version 1.0
 */
public class Board {

    private int numRows;
    private int numColumns;
    private int dimension;
    private int numSnakes;
    private int numLadders;
    private boolean finishGame;
    private Node head;
    private Node tail;
    private Random random;


    /**
     * This is the constructor
     * @param numRows
     * @param numColumns
     */
    public Board(int numRows, int numColumns, int numSnakes, int numLadders) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.dimension = numColumns*numRows;
        this.numLadders = numLadders;
        this.numSnakes = numSnakes;
        head = new Node(1);
        random= new Random();
        finishGame=false;
        createBoard(head, 2);
        createSnakes(0);
        createLadders(0);
    }

    /**
     * This method create board
     * @param prev this is the prev node
     * @param i this is the counter
     */
    private void createBoard(Node prev, int i){
        if(i <= dimension){
            if(i != dimension) {
                Node current = new Node(i);
                prev.setNext(current);
                current.setPrev(prev);
                createBoard(current, i + 1);
            } else {
                tail = new Node(i);
                prev.setNext(tail);
                tail.setPrev(prev);
            }
        }
    }


    private void createSnakes(int i){
        if(i<numSnakes){
            int initPosition = random.nextInt(dimension-1);

            if(initPosition<=1){
                createSnakes(i);
            }else{
                int endPosition = random.nextInt(initPosition);

                if(endPosition<=1 || endPosition>=initPosition){
                    createSnakes(i);
                }
                else {

                    Node init = get(initPosition, 1, head);
                    Node end = get(endPosition, 1, head);

                    if (init.getSnakeTail() == null && init.getSnakeHead() == null && end.getSnakeHead() == null && end.getSnakeTail() == null
                            && verify(initPosition, endPosition, 0) == false) {
                        init.setSnakeTail(end);
                        end.setSnakeHead(init);
                        init.setSnake((char) (i + 65));
                        end.setSnake((char) (i + 65));
                        createSnakes(i + 1);
                    } else {
                        createSnakes(i);
                    }
                }
            }
        }
    }

    private void createLadders(int i){
        if(i<numLadders){

            int endPosition = random.nextInt(dimension-1);
            if(endPosition<=1){
                createLadders(i);
            }else {

                int initPosition = random.nextInt(endPosition);
                if(initPosition<=1 || initPosition>=endPosition){
                    createLadders(i);
                }
                else {

                    Node init = get(initPosition, 1, head);
                    Node end = get(endPosition, 1, head);

                    if (init.getSnakeTail() == null && init.getSnakeHead() == null && end.getSnakeHead() == null && end.getSnakeTail() == null
                            && init.getLadderTop() == null && init.getLadderBottom() == null && end.getLadderTop() == null && end.getLadderBottom() == null
                            && verify(initPosition, endPosition, 0) == false) {
                        init.setLadderTop(end);
                        end.setLadderBottom(init);
                        init.setLadder((char) (i + 48));
                        end.setLadder((char) (i + 48));
                        createLadders(i + 1);
                    } else {
                        createLadders(i);
                    }
                }
            }
        }
    }

    private boolean verify(int init,int end,int lowerLimit){
        if(lowerLimit<dimension) {
            int upperLimit=lowerLimit+numRows;
            if (init <= upperLimit && init > lowerLimit) {
                if (end <= upperLimit && end > lowerLimit) {
                    return true;
                }
            }
            verify(init, end, upperLimit);
        }
        return false;
    }

    public String movePlayer(char playerToPlay){
        int movePlayerBy=rollDice();
        char player=playerToPlay;
        String msg="";
        Node previewPos = prevPlayerPos(player,1,null);
        int newNodePos = previewPos.getPosition()+movePlayerBy;
        if(newNodePos>=dimension){
            msg+="Player " + player + " won\n";
            finishGame=true;
        }else {
            if (get(newNodePos, 1, head).getSnakeTail() != null) {
                setPlayerOnNode(player, get(newNodePos, 1, head).getSnakeTail());
                msg+="Player " + player + " got a roll of " + movePlayerBy +"\n";
            } else if (get(newNodePos, 1, head).getLadderTop() != null) {
                setPlayerOnNode(player, get(newNodePos, 1, head).getLadderTop());
                msg+="Player " + player + " got a roll of " + movePlayerBy +"\n";
            } else {
                setPlayerOnNode(player, get(newNodePos, 1, head));
                msg+="Player " + player + " got a roll of " + movePlayerBy +"\n";
            }
        }
        return msg;
    }

    private void setPlayerOnNode(char playerToAdd, Node nodeToSetPlayer){
        if(nodeToSetPlayer.getPlayersOnNode()==null){
            String addFirstPlayer= "" + playerToAdd;
            Node prev= prevPlayerPos(playerToAdd,1,null);
            if(nodeToSetPlayer!=prev){
                nodeToSetPlayer.setPlayersOnNode(addFirstPlayer);
                isPlayerInNode(playerToAdd, prev.getPlayersOnNode(), 0);
                String newPlayersOnNode=removePlayer(playerToAdd,prev.getPlayersOnNode(),0);
                prev.setPlayersOnNode(newPlayersOnNode);
            }
        }
        else{
            String players= nodeToSetPlayer.getPlayersOnNode() + playerToAdd;
            Node prev= prevPlayerPos(playerToAdd,1,null);
            if(nodeToSetPlayer!=prev){
                nodeToSetPlayer.setPlayersOnNode(players);
                String newPlayersOnNode=removePlayer(playerToAdd,prev.getPlayersOnNode(),0);
                prev.setPlayersOnNode(newPlayersOnNode);
            }
        }
    }

    private Node prevPlayerPos(char player,int i,Node current){
        if(i<dimension){
            current = get(i, 1, head);
            String playersOnNode = current.getPlayersOnNode();
            if(playersOnNode==null){
                return prevPlayerPos(player,i+1,current);
            }
            else if(playersOnNode.equalsIgnoreCase("")){
                return prevPlayerPos(player,i+1,current);
            }
            else if(isPlayerInNode(player,playersOnNode,0)==true){
                return current;
            }
            else{
                return prevPlayerPos(player,i+1,current);
            }
        }
        return current;
    }

    private boolean isPlayerInNode(char player,String playersOnNode,int i){
        if(i<playersOnNode.length()){
            if(playersOnNode.charAt(i)==player){
                return true;
            }
            isPlayerInNode(player,playersOnNode,i+1);
        }
        return false;
    }

    private String removePlayer(char player,String playersOnNode,int i){
        if(i<playersOnNode.length()){
            if(playersOnNode.charAt(i)==player){
                if(playersOnNode.length()==1){
                    playersOnNode="";
                    return playersOnNode;
                }
                else {
                    playersOnNode = playersOnNode.substring(0, i) + playersOnNode.substring(i + 1);
                    return playersOnNode;
                }
            }
            removePlayer(player,playersOnNode,i+1);
        }
        return playersOnNode;
    }

    private int rollDice(){
        int diceValue = (int)(Math.random()*6)+1;
        if(diceValue>6){
            rollDice();
        }
        return diceValue;
    }

    public Node getTail() {
        return tail;
    }

    public Node get(int position, int i, Node current){
        if(i == position){
            return current;
        } else if(i==dimension) {
            return null;
        } else {
            current = current.getNext();
            return get(position, i+1, current);
        }
    }

    public Node getHead() {
        return head;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean getFinishGame() {
        return finishGame;
    }
}
