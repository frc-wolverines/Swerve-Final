package frc.robot.systems.swerve;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.montylib.swerve.DriveIOFrame;
import frc.montylib.swerve.PathFollowerFrame;
import frc.montylib.util.AlertLog;
import frc.montylib.util.DashboardLog;
import frc.montylib.util.AlertLog.StringAlertType;
import frc.montylib.util.hardware.NavX2;
import frc.robot.riointerface.AutonomousUtil;

public class Drive extends DriveIOFrame implements PathFollowerFrame {

    //Modules
    private final NEOModule[] modules;

    //Gyroscope
    private final NavX2 gyro;

    private final SwerveDriveOdometry odometryData;
    private final Field2d fieldData;

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

        odometryData = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, getRotation2d(), getModulePositions());
        fieldData = new Field2d();

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

                //Adds field data to the dashboard
                new DashboardLog("Field Data", fieldData);
            } catch (Exception e) {
                //Logs as a critical error
                new AlertLog(e.toString(), StringAlertType.CRITICAL_ERROR);
            }
        }).start();

        AutoBuilder.configureHolonomic(
            this::getPose2d, 
            this::resetPose2d, 
            this::getCurrentSpeeds, 
            this::setDesiredRelativeSpeeds, 
            DriveConstants.kDriveHolonomicConfig, 
            AutonomousUtil.isAllianceRed(), 
            this
        );
    }

    @Override
    public void periodic() {
        odometryData.update(getRotation2d(), getModulePositions());
        fieldData.setRobotPose(odometryData.getPoseMeters());
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

    @Override
    public Pose2d getPose2d() {
        return odometryData.getPoseMeters();
    }

    @Override
    public void resetPose2d(Pose2d pose) {
        odometryData.resetPosition(getRotation2d(), getModulePositions(), pose);
    }

    @Override
    public ChassisSpeeds getCurrentSpeeds() {
        return DriveConstants.kDriveKinematics.toChassisSpeeds(getModuleStates());
    }

    @Override
    public void setDesiredRelativeSpeeds(ChassisSpeeds speeds) {
        ChassisSpeeds outSpeeds = ChassisSpeeds.discretize(speeds, 0.02);

        setDesiredSpeeds(outSpeeds);
    }
}
