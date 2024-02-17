package frc.robot.commands.shooter;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.systems.handlers.ShooterCommandHandler.ShooterPivotMode;
import frc.robot.systems.handlers.ShooterCommandHandler.ShooterPreset;
import frc.robot.systems.shooter.Shooter;
import frc.robot.systems.shooter.util.ShooterState;

public class ShooterController extends Command {

  private Shooter shooterSubsystem;

  private Supplier<Double> shooterPowerSupplier, pivotPowerSupplier;

  private SlewRateLimiter shooterLimiter;

  private ShooterPivotMode pivotMode;
  private ShooterPreset preset;

  public ShooterController(ShooterPivotMode mode, ShooterPreset preset, Shooter shooter_subsystem, CommandXboxController controller) {

    this.shooterSubsystem = shooter_subsystem;

    this.shooterLimiter = new SlewRateLimiter(3);

    this.shooterPowerSupplier = () -> controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();
    this.pivotPowerSupplier = () -> controller.getLeftY();

    this.pivotMode = mode;
    this.preset = preset;

    addRequirements(shooter_subsystem);
  } 

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double shooterPower = shooterPowerSupplier.get();

    shooterPower = shooterLimiter.calculate(shooterPower);
  
    ShooterState state = new ShooterState(shooterPower, shooterPower, pivotPowerSupplier.get());

    if (pivotMode == ShooterPivotMode.MANUAL) {
      state = pivotMode.getManualState(shooterPower, shooterPower, pivotPowerSupplier.get());
    } else if (pivotMode == ShooterPivotMode.PRESET) {
      state = pivotMode.getPresetState(state, preset, shooterSubsystem.getPivotPosition());
    } else {
      state = pivotMode.getTrackedState(state, shooterSubsystem.getPivotPosition());
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
