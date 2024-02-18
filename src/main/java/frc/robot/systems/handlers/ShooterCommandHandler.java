package frc.robot.systems.handlers;

import edu.wpi.first.math.controller.PIDController;
import frc.montylib.util.LimelightHelper;
import frc.robot.systems.shooter.util.ShooterState;
import frc.robot.systems.superstructure.ShooterGoal.PositionGoal;

public class ShooterCommandHandler {

    private static double SUBWOOFER_VELOCITY = PositionGoal.SPEAKER.getShooterVelocity();
    private static double SUBWOOFER_POSITION = PositionGoal.SPEAKER.getPosition();

    private static double AMP_VELOCITY = PositionGoal.AMP.getShooterVelocity();
    private static double AMP_POSITION = PositionGoal.AMP.getPosition();

    private static double IDLE_VELOCITY = PositionGoal.IDLE.getShooterVelocity();
    private static double IDLE_POSITION = PositionGoal.IDLE.getPosition();

    private static PIDController pivotController = new PIDController(0.05, 0.0, 0.0);

    public enum ShooterPivotMode {
        MANUAL,
        TRACKED,
        PRESET;

        public ShooterState getManualState(double top_power, double bottom_power, double pivot_power) {
            return new ShooterState(top_power, bottom_power, pivot_power);
        }

        public ShooterState getTrackedState(ShooterState state) {
            return new ShooterState(
                state.top_power, 
                state.bottom_power, 
                pivotController.calculate(LimelightHelper.getTY(""), 0.0)
            );
        }

        public ShooterState getPresetState(ShooterProfile preset, double pivot_position) {
            return preset.getState(pivot_position);
        }
    }

    public enum ShooterProfile {
        SUBWOOFER,
        AMP,
        IDLE;

        public ShooterState getState(double pivot_position) {
            if (this.equals(SUBWOOFER)) {
                return new ShooterState(
                    SUBWOOFER_VELOCITY, 
                    SUBWOOFER_VELOCITY, 
                    pivotController.calculate(pivot_position, SUBWOOFER_POSITION));
            } else if (this.equals(AMP)) {
                return new ShooterState(
                    AMP_VELOCITY, 
                    AMP_VELOCITY, 
                    pivotController.calculate(pivot_position, AMP_POSITION));
            } else {
                return new ShooterState(
                    IDLE_VELOCITY, 
                    IDLE_VELOCITY, 
                    pivotController.calculate(pivot_position, IDLE_POSITION));
            }
        }
    }
}
