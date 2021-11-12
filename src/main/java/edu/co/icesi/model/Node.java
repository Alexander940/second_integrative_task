package edu.co.icesi.model;

import java.util.Objects;

public class Node {

    private int position;
    private Node next;
    private Node prev;
    private Node ladderTop;
    private Node ladderBottom;
    private Node snakeHead;
    private Node snakeTail;
    private String playersOnNode;
    private char snake;
    private char ladder;

    public Node(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getLadderTop() {
        return ladderTop;
    }

    public void setLadderTop(Node ladderTop) {
        this.ladderTop = ladderTop;
    }

    public Node getLadderBottom() {
        return ladderBottom;
    }

    public void setLadderBottom(Node ladderBottom) {
        this.ladderBottom = ladderBottom;
    }

    public Node getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Node snakeHead) {
        this.snakeHead = snakeHead;
    }

    public Node getSnakeTail() {
        return snakeTail;
    }

    public void setSnakeTail(Node snakeTail) {
        this.snakeTail = snakeTail;
    }

    public char getSnake() {
        return snake;
    }

    public void setSnake(char snake) {
        this.snake = snake;
    }

    public char getLadder() {
        return ladder;
    }

    public void setLadder(char ladder) {
        this.ladder = ladder;
    }

    public String getPlayersOnNode() {
        return playersOnNode;
    }

    public void setPlayersOnNode(String playersOnNode) {
        this.playersOnNode = playersOnNode;
    }
}
