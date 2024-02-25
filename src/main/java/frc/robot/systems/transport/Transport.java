package frc.robot.systems.transport;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.montylib.kinematics.TransportState;
import frc.montylib.util.DashboardLog;
import frc.montylib.util.hardware.NEO;
import frc.robot.systems.transport.util.TransportConfig;

public class Transport extends SubsystemBase {
    
    private final NEO intakeMotor;
    private final NEO beltMotor;
    private final NEO leftPivotMotor, rightPivotMotor;

    private final PIDController pivotController;

    public Transport(TransportConfig config) {

        intakeMotor = new NEO(config.intake_id);
        beltMotor = new NEO(config.belt_id);
        leftPivotMotor = new NEO(config.pivot_ids[0]);
        rightPivotMotor = new NEO(config.pivot_ids[1]);

        intakeMotor.setInverted(config.intake_direction.getAsBoolean());
        beltMotor.setInverted(config.belt_direction.getAsBoolean());
        leftPivotMotor.setInverted(config.pivot_directions[0].getAsBoolean());
        rightPivotMotor.setInverted(config.pivot_directions[1].getAsBoolean());

        intakeMotor.setIdleMode(IdleMode.kBrake);
        beltMotor.setIdleMode(IdleMode.kCoast);
        leftPivotMotor.setIdleMode(IdleMode.kBrake);
        rightPivotMotor.setIdleMode(IdleMode.kBrake);

        pivotController = new PIDController(0, 0, 0);

    }

    @Override
    public void periodic() {
        new DashboardLog("Intake Speed", intakeMotor.getActuatedVelocity());
        new DashboardLog("Belt Speed", beltMotor.getActuatedVelocity());
        new DashboardLog("Intake Angle", leftPivotMotor.getActuatedPosition());
    }

    public void resetPivotEncoders() {
        leftPivotMotor.getEncoder().setPosition(0);
        rightPivotMotor.getEncoder().setPosition(0);
    }

    public void stop() {
        intakeMotor.stopMotor();
        beltMotor.stopMotor();
        leftPivotMotor.stopMotor();
        rightPivotMotor.stopMotor();
    }

    public double getPivotPosition() {
        return leftPivotMotor.getActuatedPosition() * 2 * Math.PI;
    }

    public void setDesiredState(TransportState state) {

        intakeMotor.runToActuateVelocity(state.vi_rotations_per_second);
        beltMotor.runToActuateVelocity(state.vb_rotations_per_second);

        double pivotPower = pivotController.calculate(getPivotPosition(), state.intake_power_percentage);
        leftPivotMotor.set(pivotPower);
        rightPivotMotor.set(pivotPower);

    }
}
