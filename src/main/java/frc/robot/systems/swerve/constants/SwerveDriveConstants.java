package frc.robot.systems.swerve.constants;

public class SwerveDriveConstants {

    public static ModuleInterface leftFrontModuleInterface = new ModuleInterface(
        2, 3, 4, false, true
    );

    public static ModuleInterface rightFrontModuleInterface = new ModuleInterface(
        5, 6, 7, true, true
    );

    public static ModuleInterface leftBackModuleInterface = new ModuleInterface(
        8, 9, 10, false, true
    );

    public static ModuleInterface rightBackModuleInterface = new ModuleInterface(
        11, 12, 13, true, true
    );
}