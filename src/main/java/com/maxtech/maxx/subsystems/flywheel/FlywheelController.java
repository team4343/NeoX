package com.maxtech.maxx.subsystems.flywheel;

import com.maxtech.lib.scheduling.Loop;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;

public class FlywheelController extends Loop {
    private static FlywheelController instance;

    public static FlywheelController getInstance() {
        if (instance == null) instance = new FlywheelController();
        return instance;
    }

    private final FlywheelIO io = FlywheelIO.getInstance();
    private final FlywheelAttitude attitude = FlywheelAttitude.getInstance();

    private final PIDController controller = new PIDController(6, 1., 0);

    @Override
    public void onStart() {
        controller.reset();
    }

    @Override
    public void onLoop() {
        if (attitude.desired == null) {
            io.setPercentOutput(0);
            controller.calculate(io.getVelocity(), 0);
        } else {
            io.setVelocity(controller.calculate(io.getVelocity(), attitude.desired));
        }
    }

    @Override
    public void onEnd() {

    }

    public boolean atGoal() {
        return controller.atSetpoint();
    }
}
