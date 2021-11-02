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
    private Node firstSnake;
    private Node firstLadder;
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
        createFirstSnake();
        createSnakes(firstSnake, 1);
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

    private void createSnakes(Node prev, int i){
        if(i<numSnakes){
            int initPosition = getInitPosition();
            Node init = get(initPosition,1, head);

            int endPosition = (int) (Math.random()*initPosition+1);
            Node end = get(endPosition, 1, head);

            Node current = new Node(i,new Snake(init, end));
            prev.setNext(current);
            current.setPrev(prev);
            createSnakes(current, i+1);
        }
    }

    private void createLadders(){

    }

    public Node getTail() {
        return tail;
    }

    private void createFirstSnake(){
        int initPosition = getInitPosition();
        Node init = get(initPosition,1, head);

        int endPosition = (int) (Math.random()*initPosition+1);
        Node end = get(endPosition, 1, head);

        firstSnake = new Node(0,new Snake(init, end));
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

    public Node getFirstSnake() {
        return firstSnake;
    }
}
