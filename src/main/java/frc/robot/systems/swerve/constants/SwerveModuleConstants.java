package frc.robot.systems.swerve.constants;

import edu.wpi.first.math.util.Units;

public class SwerveModuleConstants {

    //Physical Constants
    public static final double kDriveGearRatio  = 1 / 8.14;
    public static final double kPivotGearRatio = 1 / 21.4285714;
    public static final double kWheelCircumference = Units.inchesToMeters(4) * Math.PI;
    public static final double kMaxMetersPerSecond = Units.feetToMeters(12.5);

    /** 
     * [0] Position  
     * [1] Velocity
    */
    public static final double[] kDriveConversions = {
        kDriveGearRatio * kWheelCircumference,
        (kDriveGearRatio * kWheelCircumference) / 60
    };

    /** 
     * [0] Position  
     * [1] Velocity
    */
    public static final double[] kPivotConversions = {
        kPivotGearRatio * Math.PI * 2,
        (kPivotGearRatio * Math.PI * 2) / 60
    };
}
