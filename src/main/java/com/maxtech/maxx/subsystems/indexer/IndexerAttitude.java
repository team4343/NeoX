package com.maxtech.maxx.subsystems.indexer;

public class IndexerAttitude {
    private static IndexerAttitude instance;

    public static IndexerAttitude getInstance() {
        if (instance == null) instance = new IndexerAttitude();
        return instance;
    }

    private double desiredBottomPercentOutput;
    private double desiredTopPercentOutput;

    public void setDesiredBottomPercentOutput(double bottomPercentOutput) {
        this.desiredBottomPercentOutput = bottomPercentOutput;
    }

    public void setDesiredTopPercentOutput(double topPercentOutput) {
        this.desiredTopPercentOutput = topPercentOutput;
    }

    public double getDesiredBottomPercentOutput() {
        return desiredBottomPercentOutput;
    }

    public double getDesiredTopPercentOutput() {
        return desiredTopPercentOutput;
    }
}
