package frc.robot.systems.shooter;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.kinematics.ShooterState;
import frc.montylib.util.DashboardLog;
import frc.montylib.util.hardware.Falcon500;
import frc.montylib.util.hardware.NEO;
import frc.robot.systems.shooter.util.ShooterConfig;

public class Shooter extends SubsystemBase {

    private final Falcon500 topShooterMotor, bottomShooterMotor;
    private final Falcon500 pivotMotor;
    private final NEO primerMotor;

    public Shooter(ShooterConfig config) {
        topShooterMotor = new Falcon500(config.top_shooter_id);
        bottomShooterMotor = new Falcon500(config.bottom_shooter_id);
        pivotMotor = new Falcon500(config.pivoter_id);
        primerMotor = new NEO(config.primer_id);

        topShooterMotor.setInverted(config.top_shooter_direction.getAsBoolean());
        bottomShooterMotor.setInverted(config.bottom_shooter_direction.getAsBoolean());
        pivotMotor.setInverted(config.pivoter_direction.getAsBoolean());
        primerMotor.setInverted(config.primer_direction.getAsBoolean());

        topShooterMotor.setNeutralMode(NeutralModeValue.Coast);
        bottomShooterMotor.setNeutralMode(NeutralModeValue.Coast);
        pivotMotor.setNeutralMode(NeutralModeValue.Brake);
        primerMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        new DashboardLog("Top Wheel Velocity", topShooterMotor.getActuatedVelocity());
        new DashboardLog("Bottom Wheel Velocity", bottomShooterMotor.getActuatedVelocity());
        new DashboardLog("Primer Wheel Velocity", primerMotor.getActuatedVelocity());
        new DashboardLog("Pivot Position", pivotMotor.getActuatedPosition());
    }

    public void resetPivot() {
        pivotMotor.setPosition(0);
    }

    public double getPivotPosition() {
        return pivotMotor.getPosition().getValueAsDouble();
    }

    public void setDesiredState(ShooterState state) {

        topShooterMotor.runToActuateVelocity(state.vt_rotations_per_second);
        bottomShooterMotor.runToActuateVelocity(state.vb_rotations_per_second);
        pivotMotor.set(state.pivot_power_percentage);
        primerMotor.runToActuateVelocity(state.vp_rotations_per_second);

    }

    public void stop() {
        topShooterMotor.stopMotor();
        bottomShooterMotor.stopMotor();
        pivotMotor.stopMotor();
    }
}
