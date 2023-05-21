package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

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

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Node<T>[] nodes = new Node[2];
        Arrays.asList(elements).stream().forEach(i -> {
            nodes[0] = nodes[1];
            nodes[1] = new Node<>();
            nodes[1].setElement(i);
            if (linkedList.getSize() > 0) {
                nodes[0].setNextNode(nodes[1]);
            } else {
                linkedList.setHead(nodes[1]);
            }
            linkedList.setTail(nodes[1]);
            linkedList.size++;
        });

        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> node = new Node<>();
        node.setElement(element);
        if (this.size < 1) {
            this.setHead(node);
        } else {
            this.getTail().setNextNode(node);
        }
        this.setTail(node);
        this.size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Node<T> newNode = new Node<>();
        newNode.setElement(element);

        if (index < 0 || (!this.isEmpty() && this.size - 1 < index && this.size() != index)) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            Node<T> nextNode = this.getHead();
            this.setHead(newNode);
            if (this.isEmpty()) {
                this.setTail(newNode);
            } else {
                this.getHead().setNextNode(nextNode);
                nextNode.setNextNode(this.getTail());
            }
        } else if (!this.isEmpty() && this.size == index) {
            this.getTail().setNextNode(newNode);
            this.setTail(newNode);
        } else {
            Node<T> previousNode = this.getHead();
            Node<T> nextNode = null;
            Node<T> indexNode = null;
            int count = 0;
            while (count < index - 1) {
                previousNode = previousNode.getNextNode();
                count++;
            }
            indexNode = previousNode.getNextNode();
            nextNode = indexNode.getNextNode();
            previousNode.setNextNode(newNode);
            newNode.setNextNode(indexNode);
            indexNode.setNextNode(nextNode);
        }
        this.size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        Node<T> newNode = new Node<>();
        newNode.setElement(element);

        if (index < 0 || (!this.isEmpty() && this.size - 1 < index) || (this.isEmpty() && index == 0)) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            this.setHead(newNode);
            if (this.isEmpty()) {
                this.setTail(newNode);
            }
        } else {
            Node<T> previousNode = this.getHead();
            Node<T> nextNode = null;
            Node<T> indexNode = null;
            int count = 0;
            while (count < index - 1) {
                previousNode = previousNode.getNextNode();
                count++;
            }
            indexNode = previousNode.getNextNode();
            nextNode = indexNode.getNextNode();
            previousNode.setNextNode(newNode);
            newNode.setNextNode(nextNode);
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (this.size - 1 < index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> indexNode = this.getHead();
        int count = 0;
        while (count < index) {
            indexNode = indexNode.getNextNode();
            count++;
        }
        return indexNode.getElement();
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (this.getSize() < 1) {
            throw new NoSuchElementException();
        }
        return this.getHead().getElement();
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (this.getSize() < 1) {
            throw new NoSuchElementException();
        }
        return this.getTail().getElement();
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        Node<T> removedNode = null;
        if (this.size - 1 < index) {
            throw new IndexOutOfBoundsException();
        } else if (this.size == 1 && index == 0) {
            removedNode = this.getHead();
            this.setHead(null);
            this.setTail(null);
        } else {
            Node<T> previousNode = this.getHead();
            Node<T> nextNode = null;
            int count = 0;
            if (index == 0) {
                this.setHead(previousNode.getNextNode());
                removedNode = previousNode;
            } else {
                while (count < index - 1) {
                    previousNode = previousNode.getNextNode();
                    count++;
                }
                removedNode = previousNode.getNextNode();
                nextNode = removedNode.getNextNode();
                previousNode.setNextNode(nextNode);
            }
        }
        this.size--;
        return removedNode.getElement();
    }


    /**
     * Checks if a specific exists in the list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        boolean contains = false;
        if (!this.isEmpty()) {
            Node<T> nextNode = this.getHead();
            while (null != nextNode) {
                if (null != nextNode.getNextNode() && nextNode.getElement().equals(element)) {
                    contains = true;
                    break;
                }
                nextNode = nextNode.getNextNode();
            }
        }
        return contains;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        if (this.getSize() < 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.getSize();
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        this.setSize(0);
        this.setHead(null);
        this.setTail(null);
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

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }
}
