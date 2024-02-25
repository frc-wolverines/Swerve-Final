package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.kinematics.ShooterState;
import frc.montylib.util.LimelightHelper;
import frc.montylib.util.PositionController;
import frc.robot.systems.shooter.Shooter;
import frc.robot.systems.shooter.ShooterConstants;
import frc.robot.systems.shooter.util.ShooterGoal.ShooterMode;
import frc.robot.systems.shooter.util.ShooterGoal.ShooterPreset;

@SuppressWarnings("unused")
public class ShooterController extends Command {

  private Shooter shooterSubsystem;
  
  private Supplier<Double> shooterSupplier, primerSupplier, pivotSupplier;

  private ShooterMode mode;
  private ShooterPreset preset;
  private PositionController controller;

  public ShooterController(ShooterMode mode, ShooterPreset preset, Shooter subsystem, CommandXboxController controller) {
    this.shooterSubsystem = subsystem;

    this.shooterSupplier = () -> controller.getLeftTriggerAxis() - controller.getRightTriggerAxis();
    this.pivotSupplier = () -> controller.getRightY();
    this.primerSupplier = () -> 
      (controller.leftBumper().getAsBoolean() ? 1.0 : controller.rightBumper().getAsBoolean() ? -1.0 : 0.0);
    
    this.mode = mode;
    this.preset = preset;
    this.controller = new PositionController(ShooterConstants.kPivotConstants, () -> shooterSubsystem.getPivotPosition());

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double shooterPower = shooterSupplier.get();
    double primerPower = primerSupplier.get();
    double pivotPower = pivotSupplier.get();

    ShooterState state;

    if (mode == ShooterMode.REACTIVE) {
      state = new ShooterState(
        shooterPower, primerPower, controller.calculate(LimelightHelper.getTY(""), 0.0));
    } else if (mode == ShooterMode.PRESET) {
      state = new ShooterState(
        shooterPower * preset.toCoefficient(), 
        primerPower, 
        controller.calculate(preset.toPosition())
      );
    } else {
      state = new ShooterState(shooterPower, primerPower, pivotPower);
    }

    shooterSubsystem.setDesiredState(state);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
