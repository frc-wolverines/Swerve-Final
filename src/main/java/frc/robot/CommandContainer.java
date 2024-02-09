package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.AlertLogger;
import frc.montylib.util.AlertLogger.StringAlertType;
import frc.robot.systems.swerve.commands.SwerveDriveResetHeading;
import frc.robot.systems.swerve.commands.SwerveDriveTeleopController;
import frc.robot.systems.swerve.subsystems.SwerveDrive;

public class CommandContainer {

  public SwerveDrive swerveSubsystem = new SwerveDrive();

  public CommandXboxController xboxController = new CommandXboxController(DriverUtil.kDriverControllerPort);

  public CommandContainer() {

    swerveSubsystem.setDefaultCommand(new SwerveDriveTeleopController(swerveSubsystem, xboxController));

    configureBindings();
  }

  private void configureBindings() {
    xboxController.start().onTrue(new SwerveDriveResetHeading(swerveSubsystem));
  }

  public Command getAutonomousCommand() {
    new AlertLogger("Could not verify integrity of configured autonomous command", StringAlertType.FAULT);
    return Commands.none();
  }
}
