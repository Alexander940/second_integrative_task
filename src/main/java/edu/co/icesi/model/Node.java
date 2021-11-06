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
    private char snake;
    private char ladder;
    private Player player;

    public Node(Player player) {
        this.player = player;
    }

    public Node(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return position == node.position && Objects.equals(next, node.next) && Objects.equals(prev, node.prev) && Objects.equals(ladder, node.ladder) && Objects.equals(snakeHead, node.snakeHead) && Objects.equals(snakeTail, node.snakeTail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, next, prev, ladder, snakeHead, snakeTail);
    }
}
