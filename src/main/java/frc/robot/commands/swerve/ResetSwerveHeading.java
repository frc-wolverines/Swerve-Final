package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.systems.swerve.Drive;

public class ResetSwerveHeading extends Command {

  private final Drive driveSubsystem;

  public ResetSwerveHeading(Drive subsystem) {

    driveSubsystem = subsystem;

  }

  @Override
  public void initialize() {
    driveSubsystem.resetHeading();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
