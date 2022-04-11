package com.maxtech.maxx.loops;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.subsystems.indexer.Indexer;
import com.maxtech.maxx.subsystems.indexer.IndexerState;
import com.maxtech.maxx.subsystems.intake.Intake;
import com.maxtech.maxx.subsystems.intake.IntakeState;
import edu.wpi.first.wpilibj.DriverStation;

public class IndexerChecker extends Loop {
    private static IndexerChecker instance;

    public static IndexerChecker getInstance() {
        if (instance == null) instance = new IndexerChecker();
        return instance;
    }

    private final Indexer indexer = Indexer.getInstance();
    private final Intake intake   = Intake.getInstance();

    private IntakeState lastSetState = IntakeState.RAISED;

    @Override
    public void onStart() {

    }

    @Override
    public void onLoop() {
        if (intake.state != lastSetState) {
            if (intake.state == IntakeState.LOWERED) {
                indexer.turnOn();
            } else {
                indexer.turnOff();
            }

            lastSetState = intake.state;
        }
    }

    @Override
    public void onEnd() {

    }
}
