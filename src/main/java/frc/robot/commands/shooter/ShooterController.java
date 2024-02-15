package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.systems.shooter.Primer;
import frc.robot.systems.shooter.Shooter;

//TODO: ADD FUNCTIONALITY AND PRESETS
public class ShooterController extends Command {

  private Shooter shooterSubsystem;
  private Primer primerSubsystem;

  private Supplier<Double> shooterPowerSupplier, primerPowerSupplier;

  public ShooterController(Shooter shooter_subsystem, Primer primer_subsystem, CommandXboxController controller) {

    this.shooterSubsystem = shooter_subsystem;
    this.primerSubsystem = primer_subsystem;

    this.shooterPowerSupplier = () -> controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();
    this.primerPowerSupplier = () -> controller.getRightY();

    addRequirements(shooter_subsystem, primer_subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
