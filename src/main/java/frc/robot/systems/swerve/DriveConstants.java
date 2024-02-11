package frc.robot.systems.swerve;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.util.Units;
import frc.montylib.util.MontyUnits;
import frc.robot.systems.swerve.ModuleConfig.Direction;

public class DriveConstants {
    //PID Constants for the pivot motor's PIDController
    public static final PIDConstants kModulePivotConstants = new PIDConstants(0.5, 0.0, 0.0);

    //An Array containing the ModuleConfig for each module [LEFTFRONT, RIGHTFRONT, LEFTBACK, RIGHTBACK]
    /**0 -> LEFTFRONT, 1 -> RIGHTFRONT, 2 -> LEFTBACK, 3 -> RIGHTBACK */
    public static final ModuleConfig[] kDriveConfig = {
        new ModuleConfig(
            2, 3, 4, Direction.FORWARD, Direction.FORWARD, Direction.REVERSE),
        new ModuleConfig(
            5, 6, 7, Direction.REVERSE, Direction.FORWARD, Direction.REVERSE),
        new ModuleConfig(
            8, 9, 10, Direction.FORWARD, Direction.FORWARD, Direction.REVERSE),
        new ModuleConfig(
            11, 12, 13, Direction.REVERSE, Direction.FORWARD, Direction.REVERSE)
    };

    //Max Module Speed and Wheel Circumference
    public static final double kMaxModuleSpeed = Units.feetToMeters(12.5); //Meters per Second
    public static final double kWheelCircumference = Units.inchesToMeters(4) * Math.PI;

    //Module Gear Ratios
    public static final double kDriveGearRatio = 1 / 8.14;
    public static final double kPivotGearRatio = 1 / 21.4286;

    //Module Encoder Conversion Factors (Drive)
    public static final double[] kDriveConversionFactors = {
        kDriveGearRatio * kWheelCircumference,
        (kDriveGearRatio * kWheelCircumference) / 60
    };

    //Module Encoder Conversion Factors (Pivot)
    public static final double[] kPivotConversionFactors = {
        kPivotGearRatio * MontyUnits.RADIANS_PER_REVOLUTION,
        (kPivotGearRatio * MontyUnits.RADIANS_PER_REVOLUTION) / 60
    };
}
