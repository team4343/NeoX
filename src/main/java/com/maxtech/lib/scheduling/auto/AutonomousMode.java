package com.maxtech.lib.scheduling.auto;

import com.maxtech.lib.scheduling.ILooper;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.auto.actions.Action;
import edu.wpi.first.wpilibj.Notifier;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An autonomous mode is a class with a name and constructor. It itself is a Looper, that has a method named runAction()
 * that runs a specified action in a loop (until it is complete).
 *
 * @see Looper
 * @see AutonomousSelector
 */
public abstract class AutonomousMode implements ILooper {
    private final ArrayList<Action> queue = new ArrayList<>();
    protected final Notifier notifier = new Notifier(this::handler);

    protected AutonomousMode(String name) {
        notifier.setName("Selected autonomous: " + name);
    }

    /**
     * Add an action to the queue. Use {@link this::start} to start the notifier.
     * @param action the action to add.
     */
    public void addAction(Action action) {
        queue.add(action);
    }

    public void addActions(Action... actions) {
        queue.addAll(Arrays.asList(actions));
    }

    /**
     * Given some action, run it by invoking the start, update, and stop methods (based on `isFinished()`).
     * @param action the action to run.
     */
    private void run(Action action) {
        action.start();

        do {
            action.update();
        } while (!action.isFinished());

        action.done();
    }

    private void handler() {
        for (Action action : queue) {
            run(action);
        }
    }

    public void start() {
        notifier.startSingle(0);
    }

    public void stop() {
        notifier.stop();
    }
}
