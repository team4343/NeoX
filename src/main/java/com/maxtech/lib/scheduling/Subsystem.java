package com.maxtech.lib.scheduling;

public abstract class Subsystem<T, U, V extends IO> {
    public abstract void register(Looper looper);
}
