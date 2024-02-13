package frc.robot.systems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.montylib.swerve.DriveIOFrame;
import frc.montylib.util.AlertLog;
import frc.montylib.util.AlertLog.StringAlertType;
import frc.montylib.util.hardware.NavX2;

public class Drive extends DriveIOFrame {

    //Modules
    private final NEOModule[] modules;

    //Gyroscope
    private final NavX2 gyro;

    /**Constructs a new instance of Drive*/
    public Drive() {

        //Defining new Modules
        modules = new NEOModule[] {
            new NEOModule(DriveConstants.kDriveConfig[0]),
            new NEOModule(DriveConstants.kDriveConfig[1]),
            new NEOModule(DriveConstants.kDriveConfig[2]),
            new NEOModule(DriveConstants.kDriveConfig[3])
        };

        //Defining Gyroscope
        gyro = new NavX2();

        //Creates a process thread to handle drive initialization
        new Thread(() -> {
            try {
                //Timeout for 1 second
                Thread.sleep(1000);

                //Resets hardware
                resetHeading();
                zeroModules();

                //Logs a successful initialization
                new AlertLog("Swerve Drive initialization successful", StringAlertType.INFO);
            } catch (Exception e) {
                //Logs as a critical error
                new AlertLog(e.toString(), StringAlertType.CRITICAL_ERROR);
            }
        }).start();
    }

    @Override
    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360); //Finds the difference between the gyro angle and 360
    }

    @Override
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading()); //Converts heading into Rotation2d
    }

    @Override
    public SwerveModulePosition[] getModulePositions() {
        //Uses a "for" loop to get SwerveModulePositions from each module in our module array
        SwerveModulePosition[] positions = new SwerveModulePosition[]{null, null, null, null};
        for (int i = 0; i < modules.length; i++) {
            positions[i] = modules[i].getPosition();
        }
        return positions;
    }

    @Override
    public SwerveModuleState[] getModuleStates() {
        //Uses a "for" loop to get SwerveModuleStates from each module in our module array
        SwerveModuleState[] states = new SwerveModuleState[]{null, null, null, null};
        for (int i = 0; i < modules.length; i++) {
            states[i] = modules[i].getState();
        }
        return states;
    }

    @Override
    public void setDesiredSpeeds(ChassisSpeeds speeds) {
        //Converts ChassisSpeeds into SwerveModuleStates to output to each module
        SwerveModuleState[] states = DriveConstants.kDriveKinematics.toSwerveModuleStates(speeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, DriveConstants.kMaxModuleSpeed);

        //Uses a "for" loop to output the desired state to each module in our module array
        for (int i = 0; i < modules.length; i++) {
            modules[i].setDesiredState(states[i]);
        }
    }

    @Override
    public void resetHeading() {
        gyro.reset(); //Resets gyroscope yaw
        new AlertLog("Gyroscope reset", StringAlertType.INFO); //Logs a successful reset message
    }

    @Override
    public void resetModules() {
        //Uses a "for" loop to reset each module in our module array
        for(int i = 0; i < modules.length; i++) {
            modules[i].resetEncoders();
        }
        new AlertLog("Modules reset", StringAlertType.INFO);
    }

    @Override
    public void stopModules() {
        //Uses a "for" loop to stop each module in our module array
        for(int i = 0; i < modules.length; i++) {
            modules[i].stop();
        }
    }
    
    /**Resets the drive module's pivot position to the current position of the absolute encoders */
    public void zeroModules() {
        //Uses a "for" loop to zero each module in our module array
        for(int i = 0; i < modules.length; i++) {
            modules[i].zeroPivotPosition();
        }
    }
}
