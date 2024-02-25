package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
  import frc.robot.systems.shooter.Shooter;
import frc.robot.systems.shooter.util.ShooterGoal.ShooterPositionMode;

public class ShooterController extends Command {

  private Shooter shooterSubsystem;
  
  private Supplier<Double> shooterSupplier, primerSupplier, pivotSupplier;

  private ShooterPositionMode mode;

  public ShooterController(Shooter subsystem, CommandXboxController controller, ShooterPositionMode mode) {
    this.shooterSubsystem = subsystem;

    this.shooterSupplier = () -> controller.getLeftTriggerAxis() - controller.getRightTriggerAxis();
    this.pivotSupplier = () -> controller.getRightY();
    this.primerSupplier = () -> 
      (controller.leftBumper().getAsBoolean() ? 1.0 : controller.rightBumper().getAsBoolean() ? -1.0 : 0.0);

    this.mode = mode;
    
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    if (mode == ShooterPositionMode.REACTIVE) {

    } else if (mode == ShooterPositionMode.MANUAL) {

    } else if (mode == ShooterPositionMode.PRESET) {
      
    }

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
