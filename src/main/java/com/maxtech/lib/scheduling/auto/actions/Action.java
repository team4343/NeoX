package com.maxtech.lib.scheduling.auto.actions;

/**
 * An action is a class with four main methods: start(), update(), done(), and isFinished, that define an action that
 * the robot does by its own. Each action class must implement this interface. See each method for more details.
 */
public interface Action {
    /**
     * This method is run before the action is started.
     */
    void start();

    /**
     * This method is run recurringly while the action is scheduled.
     */
    void update();

    /**
     * This method is run just before the action is considered complete.
     */
    void done();

    /**
     * This method indicates whether or not the action is complete. Usually, it asserts that a controller is happy with
     * its goal, or that we are at a certain position on the field (within some epsilon).
     * @return whether we are finished.
     */
    boolean isFinished();
}
