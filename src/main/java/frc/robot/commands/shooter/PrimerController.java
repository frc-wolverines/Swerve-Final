package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.systems.shooter.Primer;
import frc.robot.systems.shooter.Primer.PrimerMode;

public class PrimerController extends Command {

  private Primer primerSubsystem;

  private Supplier<Double> primerPowerSupplier;
  private PrimerMode systemMode;


  public PrimerController(PrimerMode mode, Primer subsystem, Supplier<Double> primer_power) {

    primerSubsystem = subsystem;

    primerPowerSupplier = primer_power;
    systemMode = mode;

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    primerSubsystem.setDesiredSpeed(systemMode, primerPowerSupplier.get());
  }

  @Override
  public void end(boolean interrupted) {
    primerSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
