package com.maxtech.lib.scheduling.actions;

import com.maxtech.lib.scheduling.actions.Action;

public class NoopAction implements Action {
    @Override
    public void start() {}

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}
}
