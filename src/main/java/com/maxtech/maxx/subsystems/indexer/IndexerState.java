package com.maxtech.maxx.subsystems.indexer;

public enum IndexerState {
    OFF(0, 0),
    NO_BALLS(.5, .5),
    ONE_BALL(.5, 0),
    TWO_BALL(0, 0);

    public final double bottomPercentOutput;
    public final double topPercentOutput;

    IndexerState(double bottomPercentOutput, double topPercentOutput) {
        this.bottomPercentOutput = bottomPercentOutput;
        this.topPercentOutput = topPercentOutput;
    }
}
