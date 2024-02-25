package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.AlertLog;
import frc.montylib.util.AlertLog.StringAlertType;
import frc.robot.commands.swerve.ResetSwerveHeading;
import frc.robot.commands.swerve.SwerveController;

import frc.robot.systems.handlers.SwerveDriveCommandHandler.RobotDriveMode;
import frc.robot.systems.shooter.Shooter;
import frc.robot.systems.shooter.ShooterConstants;
import frc.robot.systems.swerve.Drive;

public class CommandContainer {
  public Drive swerveDrive = new Drive();
  public Shooter shooter = new Shooter(ShooterConstants.kShooterConfig);

  public CommandXboxController driveController = new CommandXboxController(DriverConstants.kDriverControllerPort);
  public CommandXboxController operatorController = new CommandXboxController(DriverConstants.kOperatorControllerPort);

  public CommandContainer() {
    swerveDrive.setDefaultCommand(new SwerveController(RobotDriveMode.STANDARD_FIELD_CENTRIC, swerveDrive, driveController));


    configureBindings();
  }

  private void configureBindings() {

    //Different Drive modes
    driveController.leftBumper().whileTrue(new SwerveController(RobotDriveMode.STANDARD_ROBOT_RELATIVE, swerveDrive, driveController));
    driveController.rightBumper().whileTrue(new SwerveController(RobotDriveMode.TARGET_FACING_FIELD_ORIENTED, swerveDrive, driveController));
    driveController.rightBumper().and(driveController.leftBumper()).whileTrue(new SwerveController(
      RobotDriveMode.TARGET_ORBIT, swerveDrive, driveController));
    
    //Reset Drive heading
    driveController.start().onTrue(new ResetSwerveHeading(swerveDrive));
    
  }

  public Command getAutonomousCommand() {
    new AlertLog("Could not verify integrity of configured autonomous command", StringAlertType.FAULT);
    return Commands.none();
  }
}
