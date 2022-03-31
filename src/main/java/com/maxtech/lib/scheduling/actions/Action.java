package com.maxtech.lib.scheduling.actions;

public interface Action {
    void start();

    void update();

    boolean isFinished();

    void done();
}
