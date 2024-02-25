package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.AlertLog;
import frc.montylib.util.AlertLog.StringAlertType;
import frc.robot.commands.shooter.ShooterController;
import frc.robot.commands.swerve.ResetSwerveHeading;
import frc.robot.commands.swerve.SwerveController;
import frc.robot.commands.transport.TransportController;
import frc.robot.systems.handlers.SwerveDriveCommandHandler.RobotDriveMode;
import frc.robot.systems.shooter.Shooter;
import frc.robot.systems.shooter.ShooterConstants;
import frc.robot.systems.shooter.util.ShooterGoal.ShooterMode;
import frc.robot.systems.shooter.util.ShooterGoal.ShooterPreset;
import frc.robot.systems.swerve.Drive;
import frc.robot.systems.transport.Transport;
import frc.robot.systems.transport.TransportConstants;
import frc.robot.systems.transport.util.TransportGoal.TransportMode;
import frc.robot.systems.transport.util.TransportGoal.TransportPreset;

public class CommandContainer {
  public Drive swerveDrive = new Drive();
  public Shooter shooter = new Shooter(ShooterConstants.kShooterConfig);
  public Transport transport = new Transport(TransportConstants.kTransportConfig);

  public CommandXboxController driveController = new CommandXboxController(DriverConstants.kDriverControllerPort);
  public CommandXboxController operatorController = new CommandXboxController(DriverConstants.kOperatorControllerPort);

  public CommandContainer() {
    swerveDrive.setDefaultCommand(new SwerveController(RobotDriveMode.STANDARD_FIELD_CENTRIC, swerveDrive, driveController));

    shooter.setDefaultCommand(new ShooterController(ShooterMode.PRESET, ShooterPreset.IDLE, shooter, operatorController));

    transport.setDefaultCommand(new TransportController(TransportMode.PRESET, TransportPreset.UP, transport, operatorController));

    configureBindings();
  }

  private void configureBindings() {

    //Different Drive modes
    driveController.leftBumper().whileTrue(new SwerveController(RobotDriveMode.STANDARD_ROBOT_RELATIVE, swerveDrive, driveController));
    driveController.rightBumper().whileTrue(new SwerveController(RobotDriveMode.TARGET_FACING_FIELD_ORIENTED, swerveDrive, driveController));
    driveController.rightBumper().and(driveController.leftBumper()).whileTrue(new SwerveController(
      RobotDriveMode.TARGET_ORBIT, swerveDrive, driveController));

    //Shooter Modes
    operatorController.rightStick().whileTrue(new ShooterController(ShooterMode.MANUAL, null, shooter, operatorController));
    operatorController.y().whileTrue(new ShooterController(ShooterMode.PRESET, ShooterPreset.AMP_SHOOT, shooter, operatorController));
    operatorController.a().whileTrue(new ShooterController(ShooterMode.PRESET, ShooterPreset.SUBWOOFER_SHOOT, shooter, operatorController));
    operatorController.b().whileTrue(new ShooterController(ShooterMode.REACTIVE, null, shooter, operatorController));
    
    //Transport Modes
    operatorController.leftStick().whileTrue(new TransportController(TransportMode.MANUAL, null, transport, operatorController));
    operatorController.povDown().whileTrue(new TransportController(TransportMode.PRESET, TransportPreset.DOWN, transport, operatorController));

    //Reset Drive heading
    driveController.start().onTrue(new ResetSwerveHeading(swerveDrive));
    
  }

  public Command getAutonomousCommand() {
    new AlertLog("Could not verify integrity of configured autonomous command", StringAlertType.FAULT);
    return Commands.none();
  }
}
