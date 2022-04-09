package com.maxtech.maxx.subsystems.flywheel;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;

public class Flywheel extends Subsystem<FlywheelState, FlywheelAttitude, FlywheelIO> {
    private static Flywheel instance;

    public static Flywheel getInstance() {
        if (instance == null) instance = new Flywheel();
        return instance;
    }

    private FlywheelState state                 = FlywheelState.AT_GOAL;
    private final FlywheelAttitude attitude     = FlywheelAttitude.getInstance();
    private final FlywheelIO io                 = FlywheelIO.getInstance();
    private final FlywheelController controller = FlywheelController.getInstance();

    @Override
    public void register(Looper looper) {
        controller.register(looper);
        looper.register(new Loop() {
            @Override
            public void onStart() {
                attitude.desired = 0;
            }

            @Override
            public void onLoop() {
                // If the controller is at the goal, but the state is not, then set it to be so. If the controller is
                // not at the goal, and the state is not already spinning, set it to be so.
                if (controller.atGoal()) {
                    if (state != FlywheelState.AT_GOAL) state = FlywheelState.AT_GOAL;
                } else {
                    if (state != FlywheelState.SPINNING) state = FlywheelState.SPINNING;
                }
            }

            @Override
            public void onEnd() {

            }
        });
    }
}
