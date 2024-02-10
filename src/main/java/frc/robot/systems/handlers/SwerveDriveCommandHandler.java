package frc.robot.systems.handlers;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class SwerveDriveCommandHandler {
    
    public static PIDController target_pid_controller = new PIDController(0.1, 0, 0);

    public static enum RobotDriveMode {
        STANDARD_FIELD_CENTRIC,
        STANDARD_ROBOT_RELATIVE,
        TARGET_FACING_FIELD_ORIENTED,
        TARGET_ORBIT,
    }

    public static ChassisSpeeds getDriveModeChassisSpeeds(RobotDriveMode mode, ChassisSpeeds speeds, double target_x, Rotation2d robot_rotation) {
        switch (mode) {
            case TARGET_FACING_FIELD_ORIENTED:
                ChassisSpeeds.fromFieldRelativeSpeeds(
                    speeds.vxMetersPerSecond, 
                    speeds.vyMetersPerSecond, 
                    target_pid_controller.calculate(target_x, 0.0), 
                    robot_rotation);
            case TARGET_ORBIT:
                ChassisSpeeds.fromRobotRelativeSpeeds(
                    speeds.vxMetersPerSecond, 
                    speeds.vyMetersPerSecond, 
                    target_pid_controller.calculate(target_x, 0.0), 
                    robot_rotation);
            case STANDARD_FIELD_CENTRIC:
                ChassisSpeeds.fromFieldRelativeSpeeds(speeds, robot_rotation);
            case STANDARD_ROBOT_RELATIVE:
                ChassisSpeeds.fromRobotRelativeSpeeds(speeds, robot_rotation);
            default:
                return new ChassisSpeeds(0, 0, 0);
        }
    }
}
