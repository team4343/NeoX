package com.maxtech.maxx.subsystems.climber;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem<ClimberState, ClimberAttitude, ClimberIO> {
    private static Climber instance;

    public static Climber getInstance() {
        if (instance == null) instance = new Climber();
        return instance;
    }

    private ClimberState state             = ClimberState.RETRACTED;
    private final ClimberAttitude attitude = ClimberAttitude.getInstance();
    private final ClimberIO io             = ClimberIO.getInstance();

    private Climber() {
        SmartDashboard.putData("Climber", attitude.getMechanism());
    }

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

    public void extend() {
        state = ClimberState.EXTENDED;
    }

    public void retract() {
        state = ClimberState.RETRACTED;
    }
}
