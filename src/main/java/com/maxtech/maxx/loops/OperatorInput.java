package com.maxtech.maxx.loops;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.subsystems.climber.Climber;
import com.maxtech.maxx.subsystems.drive.DriveIO;
import com.maxtech.maxx.subsystems.intake.Intake;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static com.maxtech.maxx.Constants.Ports.Drive.MASTER_CONTROLLER;

public class OperatorInput extends Loop {
    private static OperatorInput instance;

    public static OperatorInput getInstance() {
        if (instance == null) {
            instance = new OperatorInput();
        }

        return instance;
    }

    private final DriveIO driveIO = DriveIO.getInstance();
    private final Climber climber = Climber.getInstance();
    private final Intake intake   = Intake.getInstance();

    @Override
    public void onStart() {
    }

    @Override
    public void onLoop() {
        double speed = MASTER_CONTROLLER.getRightTriggerAxis() - MASTER_CONTROLLER.getLeftTriggerAxis();
        double rotation = Math.min(Math.max(Math.pow(-MASTER_CONTROLLER.getLeftX(), 3) * 2, -1), 1);
        var speeds = DifferentialDrive.arcadeDriveIK(speed, rotation, true);
        driveIO.setWheelSpeeds(speeds);

        if (MASTER_CONTROLLER.getYButtonPressed()) {
            climber.extend();
        } else if (MASTER_CONTROLLER.getYButtonReleased()) {
            climber.retract();
        }

        if (MASTER_CONTROLLER.getXButtonPressed()) {
            intake.lower();
        } else if (MASTER_CONTROLLER.getXButtonReleased()) {
            intake.raise();
        }
    }

    @Override
    public void onEnd() {

    }
}
