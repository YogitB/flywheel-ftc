package org.firstinspires.ftc.teamcode;

public class FlywheelSimMain {
    public static void main(String[] args) {
        FlywheelPlant plant = new FlywheelPlant(6000.0, 0.3, 9.2, 0.0002);
        FlywheelController controller = new FlywheelController(1.0 / plant.getKv(), 0.02, 0.1);

        double dt = 0.02;
        int substeps = 20;
        double targetRpm = 3000.0;
        double target = targetRpm * 2.0 * Math.PI / 60.0;

        System.out.println("t,targetRpm,rpm,volts");
        for (int i = 0; i <= 300; i++) {
            double t = i * dt;
            double load = t >= 1.5 ? 0.02 : 0.0;
            double volts = controller.calculate(plant.getRadPerSec(), target, dt);
            for (int s = 0; s < substeps; s++) {
                plant.update(volts, load, dt / substeps);
            }
            System.out.println(t + "," + targetRpm + "," + plant.getRpm() + "," + volts);
        }
    }
}
