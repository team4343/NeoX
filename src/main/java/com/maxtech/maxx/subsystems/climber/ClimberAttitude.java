package com.maxtech.maxx.subsystems.climber;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;

public class ClimberAttitude {
    private static ClimberAttitude instance;

    public static ClimberAttitude getInstance() {
        if (instance == null) instance = new ClimberAttitude();
        return instance;
    }

    private final ClimberIO io = ClimberIO.getInstance();

    private final Mechanism2d mechanism = new Mechanism2d(20, 110);
    private final MechanismRoot2d mechanismRoot = mechanism.getRoot("Elevator root", 10, 0);
    private final MechanismLigament2d mechanismLigament = mechanismRoot.append(new MechanismLigament2d("Elevator", io.getPosition(), 90));

    public Mechanism2d getMechanism() {
        return mechanism;
    }
}
