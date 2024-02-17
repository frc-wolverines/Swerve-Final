package frc.robot.systems.shooter;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.util.hardware.NEO;
import frc.robot.systems.shooter.util.ShooterConfig;

public class Primer extends SubsystemBase {

    private final NEO primerMotor;

    public Primer(ShooterConfig config) {
        primerMotor = new NEO(config.primer_id);
        primerMotor.setInverted(config.primer_direction.getAsBoolean());
        primerMotor.setIdleMode(IdleMode.kBrake);
    }

    public void resetPrimer() {
        primerMotor.getEncoder().setPosition(0);
    }

    public void setDesiredSpeed(PrimerMode mode, double power) {
        if (mode == PrimerMode.BRAKE) {
            primerMotor.setIdleMode(IdleMode.kBrake);
        } else if (mode == PrimerMode.NO_BRAKE) {
            primerMotor.setIdleMode(IdleMode.kCoast);
        } else {             
            primerMotor.setIdleMode(IdleMode.kBrake);
        }

        primerMotor.set(power);
    }

    public void stop() {
        primerMotor.stopMotor();
    }

    public enum PrimerMode {
        NO_BRAKE,
        BRAKE
    }
}
