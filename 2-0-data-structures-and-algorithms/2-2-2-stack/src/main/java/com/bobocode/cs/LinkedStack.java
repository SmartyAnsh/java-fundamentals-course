package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

import java.util.Arrays;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> head;

    private int stackSize;

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> linkedStack = new LinkedStack<T>();
        Node<T> node[] = new Node[1];
        Arrays.stream(elements).forEach(i -> {
            node[0] = new Node<>();
            node[0].setElement(i);
            if (linkedStack.getHead() != null) {
                node[0].setNextNode(linkedStack.getHead());
            }
            linkedStack.setHead(node[0]);
            linkedStack.stackSize++;
        });
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if (null == element) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node();
        newNode.setElement(element);
        if (size() > 0) {
            newNode.setNextNode(this.getHead());
        }
        this.setHead(newNode);
        this.stackSize++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        Node<T> head = this.getHead();
        this.setHead(head.getNextNode());
        this.stackSize--;
        return head.getElement();
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.stackSize;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.stackSize > 0 ? false : true;
    }

    static class Node<T> {

        private T element;
        private Node nextNode;

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
