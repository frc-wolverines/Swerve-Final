package frc.montylib.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public abstract interface DriveFrame {
    //Feedback
    public abstract double getHeading();
    public abstract Rotation2d getRotation2d();
    public abstract SwerveModulePosition[] getModulePositions();
    public abstract SwerveModuleState[] getModuleStates(); 

    //Movement
    public abstract void setDesiredSpeeds(ChassisSpeeds speeds);

    //Utility
    public abstract void resetHeading();
    public abstract void resetModules();
    public abstract void stopModules();
}
