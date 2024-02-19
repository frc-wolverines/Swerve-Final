package frc.montylib.swerve;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public interface PathFollowerFrame {

    public abstract Pose2d getPose2d();
    public abstract void resetPose2d(Pose2d pose);
    public abstract ChassisSpeeds getCurrentSpeeds();
    public abstract void setDesiredRelativeSpeeds(ChassisSpeeds speeds);
    
}
