package frc.robot.systems.shooter;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.systems.shooter.util.ShooterConfig;
import frc.robot.systems.shooter.util.ShooterState;

public class Shooter extends SubsystemBase {

    private final TalonFX topShooterMotor, bottomShooterMotor;
    private final TalonFX pivotMotor;

    public Shooter(ShooterConfig config) {
        topShooterMotor = new TalonFX(config.top_shooter_id);
        bottomShooterMotor = new TalonFX(config.bottom_shooter_id);
        pivotMotor = new TalonFX(config.pivoter_id);

        topShooterMotor.setInverted(config.top_shooter_direction.getAsBoolean());
        bottomShooterMotor.setInverted(config.bottom_shooter_direction.getAsBoolean());
        pivotMotor.setInverted(config.pivoter_direction.getAsBoolean());
    }

    public void resetPivot() {
        pivotMotor.setPosition(0);
    }

    public double getPivotPosition() {
        return pivotMotor.getPosition().getValueAsDouble();
    }

    public void setDesiredState(ShooterState state) {

        topShooterMotor.set(state.top_power);
        bottomShooterMotor.set(state.bottom_power);
        pivotMotor.set(state.pivot_power);

    }

    public void stop() {
        topShooterMotor.stopMotor();
        bottomShooterMotor.stopMotor();
        pivotMotor.stopMotor();
    }
}
