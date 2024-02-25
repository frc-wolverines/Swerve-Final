package frc.robot.systems.shooter.util;

import frc.montylib.kinematics.ShooterPosition;
import frc.montylib.kinematics.ShooterState;
import frc.montylib.util.LimelightHelper;
import frc.robot.systems.shooter.ShooterConstants;

public class ShooterGoal {

    public enum ShooterPositionMode {
        MANUAL,
        REACTIVE,
        PRESET
    }

    public enum ShooterGoalPreset {
        IDLE,
        SPEAKER,
        AMP,
        SOURCE;

        public double getPosition() {
            switch (this) {
                case AMP: return 1.0;
                case IDLE: return 0.25;
                case SOURCE: return 1.0;
                case SPEAKER: return 0.9;
                default: return 0.25;
            }
        }

        public double getShooterCoefficient() {
            switch (this) {
                case AMP: return 0.18;
                case IDLE: return 0.0;
                case SOURCE: return -1.0;
                case SPEAKER: return 1.0;
                default: return 0.0;
            }
        }

        public double getPrimerCoefficient() {
            switch (this) {
                case AMP: return 1.0;
                case IDLE: return 0.0;
                case SOURCE: return 0.0;
                case SPEAKER: return 1.0;
                default: return 0.0;
            }
        }
    }

    public ShooterPosition toShooterPosition(ShooterState state, ShooterPositionMode mode, ShooterGoalPreset preset, double current_pivot_position) {
        if (mode == ShooterPositionMode.MANUAL) {
            return new ShooterPosition(
                state.vt_rotations_per_second, 
                state.vb_rotations_per_second, 
                state.vp_rotations_per_second, 
                current_pivot_position + state.pivot_power_percentage
            );
        } else if (mode == ShooterPositionMode.PRESET) {
              return new ShooterPosition (
                state.vt_rotations_per_second * preset.getShooterCoefficient(), 
                state.vb_rotations_per_second * preset.getShooterCoefficient(), 
                state.vp_rotations_per_second * preset.getPrimerCoefficient(), 
                preset.getPosition()
            );
        } else {
            return new ShooterPosition(
                state.vt_rotations_per_second, 
                state.vb_rotations_per_second, 
                state.vp_rotations_per_second, 
                current_pivot_position);
        }
    }

    public ShooterState toShooterState(double vshooter, double vprimer, ShooterPositionMode mode) {
        if (mode == ShooterPositionMode.REACTIVE) {
            return new ShooterState (
                vshooter, 
                vshooter, 
                vprimer, 
                ShooterConstants.kPivotController.calculate(LimelightHelper.getTY(""), 0.0)
            );
        } else {
            return new ShooterState(0.0, 0.0, 0.0, 0.0);
        }
    }

    public double shooter_rotations_per_second;
    public double primer_rotations_per_second;
    public double angle;

    public ShooterGoal(double vs, double vp, double angle) {
        this.shooter_rotations_per_second = vs;
        this.primer_rotations_per_second = vp;
        this.angle = angle; 
    }
}
