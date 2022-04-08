package com.maxtech.maxx.subsystems.climber;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;

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
        var tab = Shuffleboard.getTab("Climber");
        tab.addString("State", () -> state.toString());
        tab.addNumber("State values", () -> state.position);
        tab.addBoolean("At goal", this::atState);

        Mechanism2d mechanism = new Mechanism2d(20, 110);
        MechanismRoot2d mechanismRoot = mechanism.getRoot("Elevator root", 10, 0);
        MechanismLigament2d mechanismLigament = mechanismRoot.append(new MechanismLigament2d("Elevator", io.getPosition(), 90));
        tab.add("Climber", mechanism);
    }

    @Override
    public void register(Looper looper) {
        io.register(looper);
    }

    public void extend() {
        state = ClimberState.EXTENDED;
        attitude.setDesired(state.position);
    }

    public void retract() {
        state = ClimberState.RETRACTED;
        attitude.setDesired(state.position);
    }

    public boolean atState() {
        return io.getPosition() == state.position;
    }
}
