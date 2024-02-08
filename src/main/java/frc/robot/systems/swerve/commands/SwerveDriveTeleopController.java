package frc.robot.systems.swerve.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.MontyMath;
import frc.robot.systems.swerve.subsystems.SwerveDrive;

public class SwerveDriveTeleopController extends Command {
  
  private final SwerveDrive drive;
  
  private Supplier<Double> xAxisSupplier, yAxisSupplier, rAxisSupplier;
  private SlewRateLimiter xAxisAccelLimiter, yAxisAccelLimiter, rAxisAccelLimiter;

  public SwerveDriveTeleopController(SwerveDrive subsystem, CommandXboxController controller) {
    this.drive = subsystem;

    this.xAxisSupplier = () -> -controller.getLeftY();
    this.yAxisSupplier = () -> controller.getLeftX();
    this.rAxisSupplier = () -> controller.getRightX();

    this.xAxisAccelLimiter = new SlewRateLimiter(3);
    this.yAxisAccelLimiter = new SlewRateLimiter(3);
    this.rAxisAccelLimiter = new SlewRateLimiter(3);

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    drive.resetHeading();
  }

  @Override
  public void execute() {
    double x = xAxisSupplier.get();
    double y = yAxisSupplier.get();
    double r = rAxisSupplier.get();

    x = MontyMath.applyValueThreshold(x, 0.15);
    y = MontyMath.applyValueThreshold(y, 0.15);
    r = MontyMath.applyValueThreshold(r, 0.15);

    x = xAxisAccelLimiter.calculate(x);
    y = yAxisAccelLimiter.calculate(y);
    r = rAxisAccelLimiter.calculate(r);

    ChassisSpeeds outputSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
      x, y, r, drive.getRotation2d()
    );

    drive.setDesiredSpeeds(outputSpeeds);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}