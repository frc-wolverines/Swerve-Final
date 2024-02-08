package frc.robot.systems.swerve.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.swerve.DriveFrame;
import frc.montylib.util.AlertLogger;
import frc.montylib.util.AlertLogger.StringAlertType;
import frc.robot.systems.swerve.constants.SwerveDriveConstants;

public class SwerveDrive extends SubsystemBase implements DriveFrame{

    private final SwerveModule leftFrontModule;
    private final SwerveModule rightFrontModule;
    private final SwerveModule leftBackModule;
    private final SwerveModule rightBackModule;

    private final AHRS IMU;

    public SwerveDrive() {
        leftFrontModule = new SwerveModule(SwerveDriveConstants.leftFrontModuleInterface);
        rightFrontModule = new SwerveModule(SwerveDriveConstants.rightFrontModuleInterface);

        leftBackModule = new SwerveModule(SwerveDriveConstants.leftBackModuleInterface);
        rightBackModule = new SwerveModule(SwerveDriveConstants.rightBackModuleInterface);

        IMU = new AHRS(Port.kMXP);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                resetHeading();
                new AlertLogger(this.getName() + " successfully initialized", StringAlertType.INFO);
            } catch (Exception e) {
                new AlertLogger("Error initializing " + this.getName(), StringAlertType.ERROR);
            }
        }).start();
    }

    @Override
    public double getHeading() {
        return Math.IEEEremainder(IMU.getAngle(), 360);
    }

    @Override
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    @Override
    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {
            leftFrontModule.getPosition(),
            rightFrontModule.getPosition(),
            leftBackModule.getPosition(),
            rightBackModule.getPosition()
        };
    }

    @Override
    public SwerveModuleState[] getModuleStates() {
        return new SwerveModuleState[] {
            leftFrontModule.getState(),
            rightFrontModule.getState(),
            leftBackModule.getState(),
            rightBackModule.getState()
        };
    }

    @Override
    public void setDesiredSpeeds(ChassisSpeeds speeds) {
        
        SwerveModuleState[] states = SwerveDriveConstants.driveKinematics.toSwerveModuleStates(speeds);

        SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveDriveConstants.kMaxChassisSpeed);

        leftFrontModule.setDesiredState(states[0]);
        rightFrontModule.setDesiredState(states[1]);

        leftBackModule.setDesiredState(states[2]);
        rightBackModule.setDesiredState(states[3]);
    }

    @Override
    public void resetHeading() {
        IMU.reset();
    }

    @Override
    public void resetModules() {
        leftFrontModule.resetEncoders();
        rightFrontModule.resetEncoders();

        leftBackModule.resetEncoders();
        rightBackModule.resetEncoders();
    }

    @Override
    public void stopModules() {
        leftFrontModule.stop();
        rightFrontModule.stop();

        leftBackModule.stop();
        rightBackModule.stop();
    }
}
