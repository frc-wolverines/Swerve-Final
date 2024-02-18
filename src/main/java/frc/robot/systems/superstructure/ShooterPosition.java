package frc.robot.systems.superstructure;

import frc.robot.systems.shooter.ShooterConstants;

public class ShooterPosition {
    public double encoder_position = 0.0;

    public static ShooterPosition fromSteps(double steps) {
        double output = 
            (Math.abs(ShooterConstants.kShooterRawPositionBounds[1]) + 
            Math.abs(ShooterConstants.kShooterRawPositionBounds[0])) / 
            ShooterConstants.kShooterSteps;
        output = output * steps;
        return new ShooterPosition(output);
    }

    public ShooterPosition(double encoderPosition) {
        encoderPosition = 0.0;
    }
}
