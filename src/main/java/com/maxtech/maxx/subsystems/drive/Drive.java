package com.maxtech.maxx.subsystems.drive;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Drive extends Subsystem<ControlState, SystemState, DriveIO> {
    private static Drive instance;

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    private ControlState controlState = ControlState.OPEN_LOOP;
    private SystemState systemState = SystemState.getInstance();
    private DriveIO io = DriveIO.getInstance();

    public void register(Looper looper) {
        var loop = new Loop() {
            @Override
            public void onStart() {
                systemState.start(new Pose2d(), new Rotation2d());
            }

            @Override
            public void onLoop() {
                switch (controlState) {
                    case OPEN_LOOP: io.setHeading(systemState.getHeading());
                }
            }

            @Override
            public void onEnd() {
                io.stop();
            }
        };

        looper.register(loop);
    }
}
