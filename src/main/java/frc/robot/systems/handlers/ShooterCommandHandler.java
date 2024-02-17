package frc.robot.systems.handlers;

import edu.wpi.first.math.controller.PIDController;
import frc.montylib.util.LimelightHelper;
import frc.robot.systems.shooter.ShooterConstants;
import frc.robot.systems.shooter.util.ShooterState;

public class ShooterCommandHandler {

    private static double SUBWOOFER_SHOOTER_COEFFICIENT = 1.0;
    private static double SUBWOOFER_POSITION = ShooterConstants.kMaxPosition;

    private static double AMP_SHOOTER_COEFFICIENT = 0.18;
    private static double AMP_POSITION = ShooterConstants.kMaxPosition;

    private static double IDLE_SHOOTER_COEFFICIENT = 0.0;
    private static double IDLE_POSITION = ShooterConstants.kIdlePosition;

    private static PIDController pivotController = new PIDController(0.05, 0.0, 0.0);

    public enum ShooterPivotMode {
        MANUAL,
        TRACKED,
        PRESET;

        public ShooterState getManualState(double top_power, double bottom_power, double pivot_power) {
            return new ShooterState(top_power, bottom_power, pivot_power);
        }

        public ShooterState getTrackedState(ShooterState state, double pivot_position) {
            return new ShooterState(
                state.top_power, 
                state.bottom_power, 
                pivotController.calculate(LimelightHelper.getTY(""), 0.0)
            );
        }

        public ShooterState getPresetState(ShooterState state, ShooterPreset preset, double pivot_position) {
            return preset.getState(state, pivot_position);
        }
    }

    public enum ShooterPreset {
        SUBWOOFER,
        AMP,
        IDLE;

        public ShooterState getState(ShooterState state, double pivot_position) {
            if (this.equals(SUBWOOFER)) {
                return new ShooterState(
                    state.top_power * SUBWOOFER_SHOOTER_COEFFICIENT, 
                    state.bottom_power * SUBWOOFER_SHOOTER_COEFFICIENT, 
                    pivotController.calculate(pivot_position, SUBWOOFER_POSITION));
            } else if (this.equals(AMP)) {
                return new ShooterState(
                    state.top_power * AMP_SHOOTER_COEFFICIENT, 
                    state.bottom_power * AMP_SHOOTER_COEFFICIENT, 
                    pivotController.calculate(pivot_position, AMP_POSITION));
            } else {
                return new ShooterState(
                    state.top_power * IDLE_SHOOTER_COEFFICIENT, 
                    state.bottom_power * IDLE_SHOOTER_COEFFICIENT, 
                    pivotController.calculate(pivot_position, IDLE_POSITION));
            }
        }
    }
}
