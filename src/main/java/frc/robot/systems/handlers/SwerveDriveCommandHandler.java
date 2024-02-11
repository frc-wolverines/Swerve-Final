package frc.robot.systems.handlers;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class SwerveDriveCommandHandler {
    
    //PID Controller used to rotate the robot so that the camera's crosshair is centered on a target
    public static PIDController target_pid_controller = new PIDController(0.05, 0, 0);

    //Object representing different possible drive modes
    public static enum RobotDriveMode {
        STANDARD_FIELD_CENTRIC, //Standard Drive Mode
        STANDARD_ROBOT_RELATIVE, //Robot Relative Drive Mode - Most likely used for intaking
        TARGET_FACING_FIELD_ORIENTED, //Standard Drive Mode but the robot is rotated towards a target
        TARGET_ORBIT, //Robot Relative Drive Mode but the robot is rotated towards a target making it orbit around the target
    }

    /**
     * Function to return a preconfigured ChassisSpeeds given several inputs
     * @param mode the desired RobotDriveMode
     * @param speeds the default NON-FIELD-CENTRIC ChassisSpeeds using user inputs
     * @param target_x the targets x value relative to the camera's crosshair
     * @param robot_rotation the current robot's rotation
     * @return a ChassisSpeeds dependant on the given mode
     */
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
