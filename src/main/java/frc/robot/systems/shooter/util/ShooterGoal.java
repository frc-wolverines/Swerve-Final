package frc.robot.systems.shooter.util;

import frc.robot.systems.shooter.ShooterConstants;

public class ShooterGoal {
    
    public static double subwooferPosition = 0.9;
    public static double idlePosition = 0.0;

    public static double subwooferCoefficient = 1.0;
    public static double ampCoefficient = 0.18;
    public static double maximumCoefficient = 1.0;
    public static double minimumCoefficient = 1.0;
    public static double idleCoefficient = 0.0;

    
    public enum ShooterMode {
        MANUAL,
        PRESET,
        REACTIVE;
    }

    public enum ShooterPreset {
        SUBWOOFER_SHOOT,
        AMP_SHOOT,
        MAXIMUM,
        MINIMUM,
        IDLE;

        public double toPosition() {
            switch (this) {
                case SUBWOOFER_SHOOT: return subwooferPosition;
                case AMP_SHOOT: return ShooterConstants.kPositionMax;
                case MAXIMUM: return ShooterConstants.kPositionMax;
                case MINIMUM: return ShooterConstants.kPositionMin;
                case IDLE: return idlePosition;
                default: return 0.0;
            }
        }

        public double toCoefficient() {
            switch (this) {
                case SUBWOOFER_SHOOT: return subwooferCoefficient;
                case AMP_SHOOT: return ampCoefficient;
                case MAXIMUM: return maximumCoefficient;
                case MINIMUM: return minimumCoefficient;
                case IDLE: return idleCoefficient;
                default: return 1.0;
            }
        }
    }

}
