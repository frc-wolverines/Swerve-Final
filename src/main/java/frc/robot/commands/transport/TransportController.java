package frc.robot.commands.transport;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.systems.transport.Transport;

public class TransportController extends Command {

  private Transport transportSubsystem;
  private Supplier<Double> transportPowerSupplier;

  public TransportController(Transport subsystem, Supplier<Double> transport_power) {
    this.transportSubsystem = subsystem;
    this.transportPowerSupplier = transport_power;

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    transportSubsystem.setDesiredPower(transportPowerSupplier.get());
  }

  @Override
  public void end(boolean interrupted) {
    transportSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
