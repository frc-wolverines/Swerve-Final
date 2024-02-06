package frc.montylib.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public abstract class ModuleFrame {
    //Feedback
    public abstract double getDrivePosition();
    public abstract double getDriveVelocity();
    public abstract double getPivotPosition();
    public abstract double getPivotVelocity();
    public abstract Rotation2d getPivotRotation2d();
    public abstract SwerveModuleState getState();
    public abstract SwerveModulePosition getPosition();

    //Movement
    public abstract void setDesiredState(SwerveModuleState state);

    //Utility
    public abstract void resetEncoders();
    public abstract void stop();
}
