package frc.robot.systems.swerve;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.montylib.swerve.ModuleIOFrame;
import frc.montylib.util.hardware.NEOv1;
import frc.robot.systems.swerve.util.ModuleConfig;
import frc.robot.systems.swerve.util.ModuleConfig.Direction;

public class NEOModule extends ModuleIOFrame {

    //NEO Brushless v1.1 Motors
    private NEOv1 driveMotor, pivotMotor = null;
    
    //Encoders - Relative binded to motors and CANcoder absolute encoder
    private RelativeEncoder driveEncoder, pivotEncoder = null;
    private CANcoder absoluteEncoder = null;

    //PIDController for pivot motion and absolute encoder direction (Module may be inverted like MK4i)
    private PIDController pivotController = null;
    private Direction absoluteEncoderDirection = null;

    /**
     * Constructs a new instance of NEOModule
     * @param config the module config to use when initializing hardware
     */
    public NEOModule(ModuleConfig config) {
        //Motors
        driveMotor = new NEOv1(config.drive_id);
        pivotMotor = new NEOv1(config.pivot_id);

        //Motor directions
        driveMotor.setInverted(ModuleConfig.getDirectionAsBoolean(config.drive_motor_direction));
        pivotMotor.setInverted(ModuleConfig.getDirectionAsBoolean(config.pivot_motor_direction));

        //Drive Encoder
        driveEncoder = driveMotor.getEncoder();

        //Drive Encoder Setup
        driveEncoder.setPositionConversionFactor(DriveConstants.kDriveConversionFactors[0]);
        driveEncoder.setVelocityConversionFactor(DriveConstants.kDriveConversionFactors[1]);

        //Pivot Encoder
        pivotEncoder = pivotMotor.getEncoder();

        //Pivot Encoder Setup
        pivotEncoder.setPositionConversionFactor(DriveConstants.kPivotConversionFactors[0]);
        pivotEncoder.setVelocityConversionFactor(DriveConstants.kPivotConversionFactors[1]);

        //CANcoder and CANcoder direction
        absoluteEncoder = new CANcoder(config.can_coder_id);
        absoluteEncoderDirection = config.can_coder_direction;

        //PIDController and PIDController setup
        pivotController = new PIDController(
            DriveConstants.kModulePivotConstants.kP, 
            DriveConstants.kModulePivotConstants.kI, 
            DriveConstants.kModulePivotConstants.kD
        );
        pivotController.enableContinuousInput(-Math.PI, Math.PI);
    }

    @Override
    public double getDrivePosition() {
        return driveEncoder.getPosition(); //Returns wheel position in meters traveled
    }

    @Override
    public double getDriveVelocity() {
        return driveEncoder.getVelocity(); //Returns wheel velocity in meters per second
    }

    @Override
    public double getPivotPosition() {
        return pivotEncoder.getPosition(); //Returns wheel pivot position in radians traveled
    }

    @Override
    public double getPivotVelocity() {
        return pivotEncoder.getVelocity(); //Returns wheel pivot position in radians per second
    }

    @Override
    public Rotation2d getPivotRotation2d() {
        return Rotation2d.fromRadians(getPivotPosition()); //Returns wheel pivot position in the form of a Rotation2d object
    }

    public double getPivotAbsolutePosition() {
        //Returns the current wheel absolute pivot position in radians traveled
        return absoluteEncoderDirection == Direction.REVERSE ? 
            -absoluteEncoder.getAbsolutePosition().getValueAsDouble() : absoluteEncoder.getAbsolutePosition().getValueAsDouble();
    }

    @Override
    public SwerveModuleState getState() {
        //Returns an object representing the current state of the module
        return new SwerveModuleState(getDriveVelocity(), getPivotRotation2d()); 
    }

    @Override
    public SwerveModulePosition getPosition() {
        //Returns an object representing all of the module's motions
        return new SwerveModulePosition(getDrivePosition(), getPivotRotation2d());
    }

    @Override
    public void setDesiredState(SwerveModuleState state) {
        
        //Discards miniscule values
        if (state.speedMetersPerSecond < 0.001) {
            stop();
            return;
        }

        //Runs optimization algorithm to minimize distance traveled by the pivot motor
        SwerveModuleState.optimize(state, getPivotRotation2d());
        
        //Sets the output of the drive motor
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kMaxModuleSpeed);

        //Sets the desired output of the pivot motor calculated through a PIDController
        pivotMotor.set(pivotController.calculate(getPivotPosition(), state.angle.getRadians()));
    }

    @Override
    public void resetEncoders() {
        //Resets the position of both relative encoders
        driveEncoder.setPosition(0);
        pivotEncoder.setPosition(0);
    }

    @Override
    public void stop() {
        //Stops the output to both of the module motors
        driveMotor.stopMotor();
        pivotMotor.stopMotor();
    }

    public void zeroPivotPosition() {
        /* 
        * Sets the current position of the pivot's relative encoder to the 
        * current position of the absolute encoder to effectively reset the encoders position
        */
        pivotEncoder.setPosition(getPivotAbsolutePosition()); 
    }
}
