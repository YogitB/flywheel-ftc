package org.firstinspires.ftc.teamcode;

public class FlywheelController {
    public double kV;
    public double kP;
    public double kI;
    private double integral = 0.0;

    public FlywheelController(double kV, double kP, double kI) {
        this.kV = kV;
        this.kP = kP;
        this.kI = kI;
    }

    public double calculate(double measuredRadPerSec, double targetRadPerSec, double dt) {
        double error = targetRadPerSec - measuredRadPerSec;
        integral += error * dt;
        double volts = kV * targetRadPerSec + kP * error + kI * integral;
        return Math.max(-12.0, Math.min(12.0, volts));
    }

    public void reset() {
        integral = 0.0;
    }
}
