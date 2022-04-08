package com.maxtech.maxx;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.XboxController;

public class Constants {
    public static class Drive {
        public static final double TRACK_WIDTH_METERS = 1;
        public static final DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);
    }

    public static class Ports {
        public static class Drive {
            public static final XboxController MASTER_CONTROLLER = new XboxController(0);
        }
    }
}
