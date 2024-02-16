package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.AlertLog;
import frc.montylib.util.AlertLog.StringAlertType;
import frc.robot.commands.swerve.ResetSwerveHeading;
import frc.robot.commands.swerve.SwerveController;
import frc.robot.systems.handlers.SwerveDriveCommandHandler.RobotDriveMode;
import frc.robot.systems.swerve.Drive;

public class CommandContainer {
  public Drive swerveDrive = new Drive();

  public CommandXboxController xboxController = new CommandXboxController(DriverUtil.kDriverControllerPort);

  public CommandContainer() {
    swerveDrive.setDefaultCommand(new SwerveController(RobotDriveMode.STANDARD_FIELD_CENTRIC, swerveDrive, xboxController));

    configureBindings();
  }

  private void configureBindings() {

    //Different Drive modes
    xboxController.leftBumper().whileTrue(new SwerveController(RobotDriveMode.STANDARD_ROBOT_RELATIVE, swerveDrive, xboxController));
    xboxController.rightBumper().whileTrue(new SwerveController(RobotDriveMode.TARGET_FACING_FIELD_ORIENTED, swerveDrive, xboxController));
    xboxController.rightBumper().and(xboxController.leftBumper()).whileTrue(new SwerveController(
      RobotDriveMode.TARGET_ORBIT, swerveDrive, xboxController));
    
    //Reset Drive heading
    xboxController.start().onTrue(new ResetSwerveHeading(swerveDrive));
    
  }

  public Command getAutonomousCommand() {
    new AlertLog("Could not verify integrity of configured autonomous command", StringAlertType.FAULT);
    return Commands.none();
  }
}
