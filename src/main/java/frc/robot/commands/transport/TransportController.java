package frc.robot.commands.transport;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.kinematics.TransportState;
import frc.montylib.util.PositionController;
import frc.robot.systems.transport.Transport;
import frc.robot.systems.transport.TransportConstants;
import frc.robot.systems.transport.util.TransportGoal.TransportMode;
import frc.robot.systems.transport.util.TransportGoal.TransportPreset;

public class TransportController extends Command {

  private Transport transportSubsystem;

  private Supplier<Double> intakeSupplier, beltSupplier, pivotSupplier;

  private TransportMode mode;
  private TransportPreset preset;

  private PositionController controller;

  public TransportController(TransportMode mode, TransportPreset preset, Transport subsystem, CommandXboxController controller) {
    this.transportSubsystem = subsystem;

    this.intakeSupplier = () -> 
      (controller.povDown().getAsBoolean() ? 1.0 : controller.povUp().getAsBoolean() ? -1.0 : 0.0);
    this.beltSupplier = () ->
      (controller.povDown().getAsBoolean() ? 1.0 : controller.povUp().getAsBoolean() ? -1.0 : 0.0);
    this.pivotSupplier = () -> 
      (controller.leftStick().getAsBoolean() ? controller.getLeftY() : 0.0);

    this.mode = mode;
    this.preset = preset;

    this.controller = new PositionController(TransportConstants.kPivotConstants, () -> transportSubsystem.getPivotPosition());
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double intakePower = intakeSupplier.get();
    double beltPower = beltSupplier.get();
    double pivotPower = pivotSupplier.get();

    TransportState state;

    if (mode == TransportMode.PRESET) {
      state = new TransportState(intakePower, beltPower, controller.calculate(preset.getPosition()));
    } else {
      state = new TransportState(intakePower, beltPower, pivotPower);
    }

    transportSubsystem.setDesiredState(state);
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
