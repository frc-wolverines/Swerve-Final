package frc.robot.systems.swerve;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import frc.montylib.util.MontyUnits;
import frc.montylib.util.Directions.MotorDirection;
import frc.robot.systems.swerve.util.ModuleConfig;

public class DriveConstants {
    //PID Constants for the pivot motor's PIDController
    public static final PIDConstants kModulePivotConstants = new PIDConstants(0.5, 0.0, 0.0);

    //An Array containing the ModuleConfig for each module [LEFTFRONT, RIGHTFRONT, LEFTBACK, RIGHTBACK]
    /**0 -> LEFTFRONT, 1 -> RIGHTFRONT, 2 -> LEFTBACK, 3 -> RIGHTBACK */
    public static final ModuleConfig[] kDriveConfig = {
        new ModuleConfig(
            2, 3, 4, MotorDirection.FORWARD, MotorDirection.FORWARD, MotorDirection.REVERSE),
        new ModuleConfig(
            5, 6, 7, MotorDirection.REVERSE, MotorDirection.FORWARD, MotorDirection.REVERSE),
        new ModuleConfig(
            8, 9, 10, MotorDirection.FORWARD, MotorDirection.FORWARD, MotorDirection.REVERSE),
        new ModuleConfig(
            11, 12, 13, MotorDirection.REVERSE, MotorDirection.FORWARD, MotorDirection.REVERSE)
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

    //Drive Kinematics Variables
    public static final double kTrackRadius = Units.feetToMeters(29) / 2;

    public static final Translation2d kLeftFrontModulePosition = new Translation2d(kTrackRadius, kTrackRadius);
    public static final Translation2d kRightFrontModulePosition = new Translation2d(kTrackRadius, -kTrackRadius);
    public static final Translation2d kLeftBackModulePosition = new Translation2d(-kTrackRadius, kTrackRadius);
    public static final Translation2d kRightBackModulePosition = new Translation2d(-kTrackRadius, -kTrackRadius);

    //Drive Kinematics
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        kLeftFrontModulePosition,
        kRightFrontModulePosition,
        kLeftBackModulePosition,
        kRightBackModulePosition
    );

    //Drive HolonomicPathFollower Config
    public static final HolonomicPathFollowerConfig kDriveHolonomicConfig = new HolonomicPathFollowerConfig(
        new PIDConstants(5.0, 0.0, 0.0), 
        new PIDConstants(5.0, 0.0, 0.0), 
        kMaxModuleSpeed, 
        0.4, 
        new ReplanningConfig()
    );
}
