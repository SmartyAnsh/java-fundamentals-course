package com.bobocode.oop.data;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link FlightDao} represents a Data Access Object (DAO) for flights. The implementation is simplified, so it just
 * uses {@link HashSet} to store flight numbers.
 */
public class FlightDao {
    private Set<String> flights = new HashSet<>();

    /**
     * Stores a new flight number
     *
     * @param flightNumber a flight number to store
     * @return {@code true} if a flight number was stored, {@code false} otherwise
     */
    public boolean register(String flightNumber) {
        if (!this.flights.contains(flightNumber)) {
            this.flights.add(flightNumber);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all stored flight numbers
     *
     * @return a set of flight numbers
     */
    public Set<String> findAll() {
        return this.flights;
    }

}
