package org.firstinspires.ftc.teamcode;

public class FlywheelPlant {
    private final double resistance;
    private final double kT;
    private final double kE;
    private final double inertia;
    private double omega = 0.0;

    public FlywheelPlant(double freeSpeedRpm, double stallTorqueNm, double stallCurrentA, double inertiaKgM2) {
        resistance = 12.0 / stallCurrentA;
        kT = stallTorqueNm / stallCurrentA;
        kE = 12.0 / (freeSpeedRpm * 2.0 * Math.PI / 60.0);
        inertia = inertiaKgM2;
    }

    public void update(double volts, double loadTorqueNm, double dt) {
        double current = (volts - kE * omega) / resistance;
        double torque = kT * current - loadTorqueNm;
        omega += torque / inertia * dt;
    }

    public double getRadPerSec() {
        return omega;
    }

    public double getRpm() {
        return omega * 60.0 / (2.0 * Math.PI);
    }

    public double getKv() {
        return 1.0 / kE;
    }
}