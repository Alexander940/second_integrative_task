package edu.co.icesi.model;

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
    private Node head;
    private Node tail;

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
            int initPosition = getInitPosition();
            Node init = get(initPosition,1, head);

            int endPosition = (int) (Math.random() * initPosition);
            Node end = get(endPosition, 1, head);
            if(init.getSnakeTail() == null && init.getSnakeHead() == null && end.getSnakeHead() == null && end.getSnakeTail() == null){
                init.setSnakeTail(end);
                end.setSnakeHead(init);
                init.setSnake((char)(i+65));
                end.setSnake((char)(i+65));
                createSnakes(i + 1);
            } else {
                createSnakes(i);
            }
        }
    }

    private void createLadders(int i){
        if(i<numLadders){
            int initPosition = getInitPosition();
            Node init = get(initPosition,1, head);

            int rank = dimension-initPosition;

            int endPosition = (int) (Math.random()*rank);
            Node end = get(endPosition+initPosition, 1, head);

            if(init.getSnakeTail() == null && init.getSnakeHead() == null && end.getSnakeHead() == null && end.getSnakeTail() == null
                    && init.getLadderTop() == null && init.getLadderBottom() == null && end.getLadderTop() == null && end.getLadderBottom() == null){
                init.setLadderTop(end);
                end.setLadderBottom(init);
                init.setLadder((char)(i+48));
                end.setLadder((char)(i+48));
                createLadders(i+1);
            } else {
                createLadders(i);
            }
        }
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

    private int getInitPosition(){
        return (int) (Math.random()*dimension);
    }
}
