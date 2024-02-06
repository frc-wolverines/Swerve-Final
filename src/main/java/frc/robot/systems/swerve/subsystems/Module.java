package frc.robot.systems.swerve.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

//(#) STATUS : Unoperable
public class Module {
    
    private CANSparkMax driveMotor, pivotMotor = null;
    
    private RelativeEncoder driveEncoder, pivotEncoder = null;
    private CANcoder absoluteEncoder = null;

    private PIDController pivotController = null;

    public Module() {
        driveMotor = new CANSparkMax(0, MotorType.kBrushless);
        pivotMotor = new CANSparkMax(0, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        pivotEncoder = pivotMotor.getEncoder();

        absoluteEncoder = new CANcoder(0);

        pivotController = new PIDController(0, 0, 0);
    }

    public void resetEncoders() {
        driveEncoder.setPosition(0);
        pivotEncoder.setPosition(0);
    }
}
