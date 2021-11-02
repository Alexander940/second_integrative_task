package edu.co.icesi.model;

import java.util.Objects;

public class Node {

    private int position;
    private Node next;
    private Node prev;
    private Ladder ladder;
    private Snake snake;

    public Node(int position) {
        this.position = position;
    }

    public Node(Ladder ladder) {
        this.ladder = ladder;
    }

    public Node(int position,Snake snake) {
        this.snake = snake;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return position == node.position && Objects.equals(next, node.next) && Objects.equals(prev, node.prev) && Objects.equals(ladder, node.ladder) && Objects.equals(snake, node.snake);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, next, prev, ladder, snake);
    }
}
