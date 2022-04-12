package com.maxtech.lib.scheduling.auto.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This action runs numerous actions in parallel. After constructing this action, run it like a normal one.
 *
 * ```java
 * ParallelAction mAction = new ParallelAction(new NoopAction(), new NoopAction, new NoopAction());
 * runAction(mAction);
 * ```
 */
public class ParallelAction implements Action {
    private final ArrayList<Action> actions;

    public ParallelAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public ParallelAction(Action... actions) {
        this(Arrays.asList(actions));
    }

    @Override
    public void start() {
        actions.forEach(Action::start);
    }

    @Override
    public void update() {
        actions.forEach(Action::update);
    }

    @Override
    public boolean isFinished() {
        for (Action action : actions) if (!action.isFinished()) return false;
        return true;
    }

    @Override
    public void done() {
        actions.forEach(Action::done);
    }
}
