package com.maxtech.lib.scheduling.auto.actions;

public interface Action {
    void start();

    void update();

    void done();

    boolean isFinished();
}
