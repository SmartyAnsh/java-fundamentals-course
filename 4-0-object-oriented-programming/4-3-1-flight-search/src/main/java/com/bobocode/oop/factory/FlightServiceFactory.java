package com.bobocode.oop.factory;

import com.bobocode.oop.service.FlightService;
import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link FlightServiceFactory} is used to create an instance of {@link FlightService}
 */
public class FlightServiceFactory {

    /**
     * Create a new instance of {@link FlightService}
     *
     * @return FlightService
     */
    public FlightService createFlightService() {
        return new FlightService();
    }
}
