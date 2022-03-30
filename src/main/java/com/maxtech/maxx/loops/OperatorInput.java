package com.maxtech.maxx.loops;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.Constants;
import com.maxtech.maxx.subsystems.climber.Climber;
import com.maxtech.maxx.subsystems.climber.ClimberState;
import com.maxtech.maxx.subsystems.drive.DriveAttitude;
import com.maxtech.maxx.subsystems.drive.DriveIO;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

    private final XboxController masterController = new XboxController(Constants.Ports.Drive.MASTER);

    @Override
    public void onStart() {
    }

    @Override
    public void onLoop() {
        double speed = masterController.getRightTriggerAxis() - masterController.getLeftTriggerAxis();
        double rotation = Math.min(Math.max(Math.pow(-masterController.getLeftX(), 3) * 2, -1), 1);
        var speeds = DifferentialDrive.arcadeDriveIK(speed, rotation, true);
        driveIO.setWheelSpeeds(speeds);

        if (masterController.getYButtonPressed()) {
            climber.extend();
        } else if (masterController.getYButtonReleased()) {
            climber.retract();
        }
    }

    @Override
    public void onEnd() {

    }
}
