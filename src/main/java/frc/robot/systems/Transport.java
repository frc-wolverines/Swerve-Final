package frc.robot.systems;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.util.Directions.MotorDirection;
import frc.montylib.util.hardware.NEO;

public class Transport extends SubsystemBase {
    
    private final NEO transportMotor;

    public Transport(int motor_id, MotorDirection motor_direction) {
        transportMotor = new NEO(motor_id);
        
        transportMotor.setInverted(motor_direction.getAsBoolean());
        transportMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredPower(double power) {
        transportMotor.set(power);
    }

    public void stop() {
        transportMotor.stopMotor();
    }
}
