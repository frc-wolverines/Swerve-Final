package frc.robot.systems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.util.Directions.MotorDirection;
import frc.montylib.util.hardware.NEO;

public class Intake extends SubsystemBase {
    
    private final NEO intakeMotor;

    public Intake(int intake_id, MotorDirection intake_direction) {
        this.intakeMotor = new NEO(intake_id);
        intakeMotor.setInverted(intake_direction.getAsBoolean());
    }

    public void setDesiredPower(double power) {
        intakeMotor.set(power);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }
}
