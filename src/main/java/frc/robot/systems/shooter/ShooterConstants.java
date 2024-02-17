package frc.robot.systems.shooter;

import frc.montylib.util.Directions.MotorDirection;
import frc.robot.systems.shooter.util.ShooterConfig;

public class ShooterConstants {

    public static final double kMaxPosition = 0.0;
    public static final double kMinPosition = 0.0;
    public static final double kIdlePosition = 0.0;
    
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
}
