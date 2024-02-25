package frc.robot.systems.shooter;

import edu.wpi.first.math.controller.PIDController;
import frc.montylib.util.Directions.MotorDirection;
import frc.robot.systems.shooter.util.ShooterConfig;

public class ShooterConstants {

    public static double kPositionMin = 0.0;
    public static double kPositionMax = 0.0;

    public static PIDController kPivotController = new PIDController(0, 0, 0);

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
