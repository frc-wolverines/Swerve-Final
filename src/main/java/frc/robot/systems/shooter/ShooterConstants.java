package frc.robot.systems.shooter;

import frc.montylib.util.Directions.MotorDirection;
import frc.robot.systems.shooter.util.ShooterConfig;

public class ShooterConstants {
    
    public static final ShooterConfig kShooterConfig = new ShooterConfig (
        14, 
        15, 
        16, 
        17, 
        MotorDirection.FORWARD, 
        MotorDirection.REVERSE, 
        MotorDirection.FORWARD, 
        MotorDirection.FORWARD
    );

    public static double kIdlePosition = 9; //The pivot position in steps that will align the shooter with the transport
    public static double kShooterSteps = 55; //The number of teeth on the shooter pivot region
    public static double[] kShooterRawPositionBounds = {
        0.0, //The minimum encoder value acheivable by the shooter pivot
        0.0 //The maximum encoder value acheivable by the shooter pivot
    };
}
