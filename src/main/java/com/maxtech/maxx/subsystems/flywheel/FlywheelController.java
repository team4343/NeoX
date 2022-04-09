package com.maxtech.maxx.subsystems.flywheel;

import com.maxtech.lib.scheduling.Loop;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class FlywheelController extends Loop {
    private static FlywheelController instance;

    public static FlywheelController getInstance() {
        if (instance == null) instance = new FlywheelController();
        return instance;
    }

    private final FlywheelIO io = FlywheelIO.getInstance();
    private final FlywheelAttitude attitude = FlywheelAttitude.getInstance();

    private final ProfiledPIDController controller = new ProfiledPIDController(.5, 0.001, 0, new TrapezoidProfile.Constraints(20000, 5000));

    @Override
    public void onStart() {
        controller.reset(0);
    }

    @Override
    public void onLoop() {
        io.setVelocity(controller.calculate(io.getVelocity(), attitude.desired));
    }

    @Override
    public void onEnd() {

    }

    public boolean atGoal() {
        return controller.atGoal();
    }
}
