package frc.montylib.util;

import edu.wpi.first.math.controller.PIDController;

public class PIDConstants {
    public double kP;
    public double kI;
    public double kD;

    public PIDConstants(double p, double i, double d) {
        this.kP = p;
        this.kI = i;
        this.kD = d;
    }

    public PIDController toPIDController() {
        return new PIDController(kP, kI, kD);
    }
}