package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    private Object[] array;
    private int size;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.array = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this.array = new Object[5];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        ArrayList<T> arrayList = new ArrayList<>(elements.length);
        arrayList.array = elements;
        arrayList.size = elements.length;
        return arrayList;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (this.array.length <= this.size) {
            Object[] oldArray = this.array;
            this.array = new Object[this.size + 5];
            int[] count = new int[1];
            count[0] = 0;
            Arrays.asList(oldArray).forEach(i -> {
                this.array[count[0]] = i;
                count[0] = ++count[0];
            });
        }
        this.array[this.size] = element;
        this.size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        } else if (index == size) {
            this.add(element);
        } else {
            Object[] existingArray = this.array.clone();
            this.set(index, element);
            this.add(element);
            for (int i = index; i < existingArray.length; i++) {
                if (null != existingArray[i]) {
                    this.set(i + 1, (T) existingArray[i]);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        if (index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) this.array[index];
        }
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        } else {
            return (T) this.array[0];
        }
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        } else {
            return (T) this.array[this.size - 1];
        }
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if (index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            this.array[index] = element;
        }
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
        T removedElement = null;
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else if (index == this.size - 1) {
            removedElement = (T) this.array[index];
            this.array[index] = null;
        } else {
            Object[] existingArray = this.array.clone();
            removedElement = (T) existingArray[index];
            this.array[this.size - 1] = null;
            existingArray[index] = null;
            for (int i = index + 1; i < existingArray.length; i++) {
                if (null != existingArray[i]) {
                    this.set(i - 1, (T) existingArray[i]);
                } else {
                    break;
                }
            }
        }
        this.size--;
        return removedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        if (this.size < 1) return false;
        return Arrays.asList(this.array).stream().filter(i -> i.equals(element)).count() > 0 ? true : false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size < 1 ? true : false;
    }

    /**
     * @return amount of saved elements
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = null;
        }
        this.size = 0;
    }
}
