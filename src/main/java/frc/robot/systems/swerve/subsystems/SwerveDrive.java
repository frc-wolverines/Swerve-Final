package frc.robot.systems.swerve.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.swerve.DriveFrame;
import frc.robot.systems.swerve.subsystems.SwerveModule;

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
        
    }

    @Override
    public double getHeading() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeading'");
    }

    @Override
    public double getRotation2d() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRotation2d'");
    }

    @Override
    public SwerveModulePosition[] getModulePositions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModulePositions'");
    }

    @Override
    public SwerveModuleState[] getModuleStates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModuleStates'");
    }

    @Override
    public void setDesiredSpeeds(ChassisSpeeds speeds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDesiredSpeeds'");
    }

    @Override
    public void resetHeading() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetHeading'");
    }

    @Override
    public void resetModules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetModules'");
    }

    @Override
    public void stopModules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopModules'");
    }
    
}
