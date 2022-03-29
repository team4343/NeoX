package com.maxtech.maxx.subsystems.climber;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;

public class Climber extends Subsystem<ClimberState, ClimberAttitude, ClimberIO> {
    private static Climber instance;

    public static Climber getInstance() {
        if (instance == null) instance = new Climber();
        return instance;
    }

    private final ClimberState state       = ClimberState.RETRACTED;
    private final ClimberAttitude attitude = ClimberAttitude.getInstance();
    private final ClimberIO io             = ClimberIO.getInstance();

    @Override
    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                io.setPosition(state.position);
            }

            @Override
            public void onEnd() {

            }
        });
    }
}
