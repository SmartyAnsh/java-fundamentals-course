package com.bobocode.cs;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        Node<T> node = new Node<>();
        node.setElement(element);
        node.setNextNode(this.tail);
        if (this.size == 0) {
            this.setHead(node);
        } else {
            this.getTail().setNextNode(node);
        }
        this.setTail(node);
        this.size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        Node<T> headNode = null;
        if (this.size > 0) {
            headNode = this.getHead();
            this.setHead(headNode.getNextNode());
            this.size--;
        }

        if (this.size == 0) {
            this.setTail(null);
        }

        return headNode != null ? headNode.getElement() : null;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return this.getSize();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return this.getSize() > 0 ? false : true;
    }

    static class Node<T> {

        private T element;
        private Node<T> nextNode;

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
