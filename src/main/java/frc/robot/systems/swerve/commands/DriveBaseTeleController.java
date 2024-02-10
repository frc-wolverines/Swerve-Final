package frc.robot.systems.swerve.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.MontyMath;
import frc.montylib.util.VariableSpeedController;
import frc.montylib.util.VariableSpeedController.VariableSpeedMode;
import frc.robot.DriverUtil;
import frc.robot.systems.swerve.subsystems.SwerveDrive;

public class DriveBaseTeleController extends Command {
  
  private final SwerveDrive drivebase;
  
  private Supplier<Double> xAxisSupplier, yAxisSupplier, rAxisSupplier;
  private SlewRateLimiter xAxisAccelLimiter, yAxisAccelLimiter, rAxisAccelLimiter;
  private VariableSpeedController xSpeedController, ySpeedController, rSpeedController;

  public DriveBaseTeleController(SwerveDrive subsystem, CommandXboxController controller) {
    this.drivebase = subsystem;

    this.xAxisSupplier = () -> -controller.getLeftY();
    this.yAxisSupplier = () -> controller.getLeftX();
    this.rAxisSupplier = () -> controller.getRightX();

    this.xSpeedController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);
    xSpeedController.enableTriSpeedControl(
      DriverUtil.kTeleOpSpeeds[1], 
      DriverUtil.kTeleOpSpeeds[2], 
      DriverUtil.kTeleOpSpeeds[0]);

    this.ySpeedController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);
    ySpeedController.enableTriSpeedControl(
      DriverUtil.kTeleOpSpeeds[1], 
      DriverUtil.kTeleOpSpeeds[2], 
      DriverUtil.kTeleOpSpeeds[0]);

    this.rSpeedController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);
    rSpeedController.enableTriSpeedControl(
      DriverUtil.kTeleOpAngularSpeeds[1], 
      DriverUtil.kTeleOpAngularSpeeds[2], 
      DriverUtil.kTeleOpAngularSpeeds[0]);

    this.xAxisAccelLimiter = new SlewRateLimiter(3);
    this.yAxisAccelLimiter = new SlewRateLimiter(3);
    this.rAxisAccelLimiter = new SlewRateLimiter(3);

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double x = xAxisSupplier.get();
    double y = yAxisSupplier.get();
    double r = rAxisSupplier.get();

    x = MontyMath.applyValueThreshold(x, DriverUtil.kControlAxisThreshold);
    y = MontyMath.applyValueThreshold(y, DriverUtil.kControlAxisThreshold);
    r = MontyMath.applyValueThreshold(r, DriverUtil.kControlAxisThreshold);

    x = xAxisAccelLimiter.calculate(x);
    y = yAxisAccelLimiter.calculate(y);
    r = rAxisAccelLimiter.calculate(r);

    ChassisSpeeds outputSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
      x, y, r, drivebase.getRotation2d()
    );

    drivebase.setDesiredSpeeds(outputSpeeds);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}