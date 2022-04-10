package com.maxtech.lib.scheduling;

public interface Planner {
    void init();
    void run();
    boolean atReference();
    void stop();
}
