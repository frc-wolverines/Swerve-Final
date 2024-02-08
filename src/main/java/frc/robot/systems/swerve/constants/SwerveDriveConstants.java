package frc.robot.systems.swerve.constants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import frc.robot.systems.swerve.util.ModuleInterface;

public class SwerveDriveConstants {

    public static final double kMaxChassisSpeed = Units.feetToMeters(12.5);
    public static final double kMaxChassisAngularSpeed = 5.0 * Math.PI;

    public static final double kWheelDistance = Units.inchesToMeters(24.0);

    public static final Translation2d leftFrontModuleTranslation = new Translation2d(kWheelDistance, kWheelDistance);
    public static final Translation2d rightFrontModuleTranslation = new Translation2d(kWheelDistance, -kWheelDistance);
    public static final Translation2d leftBackModuleTranslation = new Translation2d(-kWheelDistance, kWheelDistance);
    public static final Translation2d rightBackModuleTranslation = new Translation2d(-kWheelDistance, -kWheelDistance);

    public static final SwerveDriveKinematics driveKinematics = new SwerveDriveKinematics(
        leftFrontModuleTranslation, rightFrontModuleTranslation, leftBackModuleTranslation, rightBackModuleTranslation
    );

    public static ModuleInterface leftFrontModuleInterface = new ModuleInterface(
        2, 
        3, 
        4, 
        false, 
        true
    );

    public static ModuleInterface rightFrontModuleInterface = new ModuleInterface(
        5, 
        6, 
        7, 
        true, 
        true
    );

    public static ModuleInterface leftBackModuleInterface = new ModuleInterface(
        8, 
        9, 
        10, 
        false, 
        true
    );

    public static ModuleInterface rightBackModuleInterface = new ModuleInterface(
        11, 
        12, 
        13, 
        true, 
        true
    );
}