package com.maxtech.lib.scheduling;

public abstract class Loop {
    public abstract void onStart();
    public abstract void onLoop();
    public abstract void onEnd();

    public void register(Looper looper) {
        looper.register(this);
    }
}
