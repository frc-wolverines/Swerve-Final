package frc.robot.systems.swerve.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.montylib.swerve.ModuleFrame;
import frc.robot.systems.swerve.constants.SwerveModuleConstants;
import frc.robot.systems.swerve.util.ModuleInterface;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

//(#) STATUS : Functional
public class SwerveModule extends ModuleFrame {
    //Motors
    private CANSparkMax driveMotor, pivotMotor = null;
    
    //Encoders
    private RelativeEncoder driveEncoder, pivotEncoder = null;
    private CANcoder absoluteEncoder = null;

    //PID Controller
    private PIDController pivotController = null;

    private boolean reverseAbsoluteEncoderReadings = false;

    /** Constructs a new SwerveModule instance */
    public SwerveModule(ModuleInterface moduleInterface) {

        //Motor Definition
        driveMotor = new CANSparkMax(moduleInterface.driveCAN_ID, MotorType.kBrushless);
        driveMotor.setInverted(moduleInterface.reverseDriveMotor);
        pivotMotor = new CANSparkMax(moduleInterface.pivotCAN_ID, MotorType.kBrushless);

        //Drive Encoder Definition and Configuration
        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPositionConversionFactor(SwerveModuleConstants.kDriveConversions[0]);
        driveEncoder.setVelocityConversionFactor(SwerveModuleConstants.kDriveConversions[1]);

        //Pivot Encoder Definition and Configuration
        pivotEncoder = pivotMotor.getEncoder();
        pivotEncoder.setPositionConversionFactor(SwerveModuleConstants.kPivotConversions[0]);
        pivotEncoder.setVelocityConversionFactor(SwerveModuleConstants.kPivotConversions[1]);

        //CAN-Coder Definition
        absoluteEncoder = new CANcoder(moduleInterface.absoluteEncoderCAN_ID);

        //PID Controller Definition and Configuration
        pivotController = new PIDController(0, 0, 0);
        pivotController.enableContinuousInput(-Math.PI, Math.PI);

        //Stores absolute encoder direction
        reverseAbsoluteEncoderReadings = moduleInterface.reverseAbsoluteEncoder;
    }

    //FEEDBACK FUNCTIONS
    @Override
    public double getDrivePosition() {
        return driveEncoder.getPosition(); //Units: Meters
    }

    @Override
    public double getDriveVelocity() {
        return driveEncoder.getVelocity(); //Units: Meters per Second
    }

    @Override
    public double getPivotPosition() {
        return pivotEncoder.getPosition(); //Units: Radians
    }

    @Override
    public double getPivotVelocity() {
        return pivotEncoder.getVelocity(); //Units: Radians per Second
    }

    @Override
    public Rotation2d getPivotRotation2d() {
        return Rotation2d.fromRadians(getPivotPosition());
    }

    public double getAbsolutePosition() {
        return reverseAbsoluteEncoderReadings ? 
            -absoluteEncoder.getAbsolutePosition().getValueAsDouble() : absoluteEncoder.getAbsolutePosition().getValueAsDouble(); //Units: Radians
    }

    @Override
    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), getPivotRotation2d()); //Used to represent the current state of the module
    }

    @Override
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getDrivePosition(), getPivotRotation2d()); //Used to represent the distance the module has traveled
    }

    @Override
    public void setDesiredState(SwerveModuleState state) {
        
        //Discards small speeds
        if (state.speedMetersPerSecond < 0.001) {
            stop();
            return;
        }

        //Runs optimization algorithm to minimize distance traveled by the pivot motor
        SwerveModuleState.optimize(state, getPivotRotation2d());

        driveMotor.set(state.speedMetersPerSecond / SwerveModuleConstants.kMaxMetersPerSecond);

        pivotMotor.set(
            pivotController.calculate(getPivotPosition(), state.angle.getRadians())
        );
    }

    @Override
    public void resetEncoders() {
        driveEncoder.setPosition(0.0);
        pivotEncoder.setPosition(0.0);
    }

    @Override
    public void stop() {
        driveMotor.stopMotor();
        pivotMotor.stopMotor();
    }

    /**Zeros the pivot encoder's position to the current absolute encoders position*/
    public void zeroPivotPosition() {
        pivotEncoder.setPosition(getAbsolutePosition());
    }
}
