package com.maxtech.maxx;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public class Constants {
    public static class Drive {
        public static double TRACK_WIDTH_METERS = 1;
        public static DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);
    }

    public static class Ports {
        public static class Drive {
            public static int MASTER = 0;
        }
    }
}
