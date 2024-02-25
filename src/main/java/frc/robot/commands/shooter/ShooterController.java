package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
  import frc.robot.systems.shooter.Shooter;

@SuppressWarnings("unused")
public class ShooterController extends Command {

  private Shooter shooterSubsystem;
  
  private Supplier<Double> shooterSupplier, primerSupplier, pivotSupplier;

  public ShooterController(Shooter subsystem, CommandXboxController controller) {
    this.shooterSubsystem = subsystem;

    this.shooterSupplier = () -> controller.getLeftTriggerAxis() - controller.getRightTriggerAxis();
    this.pivotSupplier = () -> controller.getRightY();
    this.primerSupplier = () -> 
      (controller.leftBumper().getAsBoolean() ? 1.0 : controller.rightBumper().getAsBoolean() ? -1.0 : 0.0);
    
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
