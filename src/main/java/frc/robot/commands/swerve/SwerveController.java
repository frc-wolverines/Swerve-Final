package frc.robot.commands.swerve;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.MontyMath;
import frc.montylib.util.VariableSpeedController;
import frc.montylib.util.VariableSpeedController.TriSpeedCalculateMode;
import frc.montylib.util.VariableSpeedController.VariableSpeedMode;
import frc.robot.DriverUtil;
import frc.robot.systems.handlers.SwerveDriveCommandHandler;
import frc.robot.systems.handlers.SwerveDriveCommandHandler.RobotDriveMode;
import frc.robot.systems.swerve.Drive;

public class SwerveController extends Command {

  //Drive Subsystem
  private final Drive driveSubsystem;

  //User input suppliers
  private Supplier<Double> xSupplier, ySupplier, rSupplier, slowSupplier, fastSupplier;
  private Supplier<Boolean> robotOrbitTrigger, robotRelativeTrigger, robotFaceTrigger;
  
  //Slew rate limiters and speed controllers
  private final SlewRateLimiter xLimiter, yLimiter, rLimiter;
  private final VariableSpeedController xController, yController, rController;

  /**
   * Constructs a new instance of SwerveDriveController
   * @param subsystem the drive subsystem used for feedback and control
   * @param controller the controller to grab user input from
   */
  public SwerveController(Drive subsystem, CommandXboxController controller) {
    //Defining Drive subsystem
    this.driveSubsystem = subsystem;

    //Defining User input joystick axis
    this.xSupplier = () -> -controller.getLeftY();
    this.ySupplier = () -> controller.getLeftX();
    this.rSupplier = () -> controller.getRightX();

    //Defining User input trigger axis
    this.slowSupplier = () -> controller.getLeftTriggerAxis();
    this.fastSupplier = () -> controller.getRightTriggerAxis();

    //User input drive mode events
    this.robotOrbitTrigger = () -> controller.leftBumper().and(controller.rightBumper()).getAsBoolean();
    this.robotRelativeTrigger = () -> controller.leftBumper().getAsBoolean();
    this.robotFaceTrigger = () -> controller.rightTrigger().getAsBoolean();

    //Defining SlewRateLimiters
    this.xLimiter = new SlewRateLimiter(3);
    this.yLimiter = new SlewRateLimiter(3);
    this.rLimiter = new SlewRateLimiter(3);

    //Defining SpeedControllers
    this.xController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);
    this.yController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);
    this.rController = new VariableSpeedController(VariableSpeedMode.TRI_SPEED);

    //SpeedController setup
    xController.enableTriSpeedControl(DriverUtil.kTeleOpSpeeds[1], DriverUtil.kTeleOpSpeeds[2], DriverUtil.kTeleOpSpeeds[0]);
    yController.enableTriSpeedControl(DriverUtil.kTeleOpSpeeds[1], DriverUtil.kTeleOpSpeeds[2], DriverUtil.kTeleOpSpeeds[0]);
    rController.enableTriSpeedControl(
      DriverUtil.kTeleOpAngularSpeeds[1], DriverUtil.kTeleOpAngularSpeeds[2], DriverUtil.kTeleOpAngularSpeeds[0]);

    //Command requirements
    addRequirements(subsystem);
  }

  @Override
public void initialize() { /*Nothing to see here*/ }

  @Override
  public void execute() {

    //Temporary variables representing each joystick axis
    double x = xSupplier.get();
    double y = ySupplier.get();
    double r = rSupplier.get();

    //Applying a deadzone where the value will be discarded
    x = MontyMath.applyValueThreshold(x, DriverUtil.kControlAxisThreshold);
    y = MontyMath.applyValueThreshold(y, DriverUtil.kControlAxisThreshold);
    r = MontyMath.applyValueThreshold(r, DriverUtil.kControlAxisThreshold);

    //Applying the SlewRateLimiter and SpeedController calculations
    x = xLimiter.calculate(x) * xController.calculate(TriSpeedCalculateMode.BOTH, fastSupplier.get(), slowSupplier.get());
    y = yLimiter.calculate(y) * yController.calculate(TriSpeedCalculateMode.BOTH, fastSupplier.get(), slowSupplier.get());
    r = rLimiter.calculate(r) * rController.calculate(TriSpeedCalculateMode.BOTH, fastSupplier.get(), slowSupplier.get());

    //Creating a temporary and output ChassisSpeeds
    ChassisSpeeds speeds = new ChassisSpeeds(x, y, r);
    ChassisSpeeds outputSpeeds;

    if (robotOrbitTrigger.get()) {
      //Defines outputSpeed to a calculated value from speeds (ORBIT)
      outputSpeeds = SwerveDriveCommandHandler.getDriveModeChassisSpeeds(
        RobotDriveMode.TARGET_ORBIT, speeds, 0.0, driveSubsystem.getRotation2d());
    } else if (robotFaceTrigger.get()) {
      //Defines outputSpeed to a calculated value from speeds (TARGET-FACING)
      outputSpeeds = SwerveDriveCommandHandler.getDriveModeChassisSpeeds(
        RobotDriveMode.TARGET_FACING_FIELD_ORIENTED, speeds, 0.0, driveSubsystem.getRotation2d());
    } else if (robotRelativeTrigger.get()) {
      //Defines outputSpeed to a calculated value from speeds (ROBOT-RELATIVE)
      outputSpeeds = SwerveDriveCommandHandler.getDriveModeChassisSpeeds(
        RobotDriveMode.STANDARD_ROBOT_RELATIVE, speeds, 0.0, driveSubsystem.getRotation2d());
    } else {
      //Defines outputSpeed to a calculated value from speeds (FIELD-CENTRIC)
      outputSpeeds = SwerveDriveCommandHandler.getDriveModeChassisSpeeds(
        RobotDriveMode.STANDARD_FIELD_CENTRIC, speeds, 0.0, driveSubsystem.getRotation2d());
    }

    //Drive output
    driveSubsystem.setDesiredSpeeds(outputSpeeds);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stopModules();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
