package frc.robot.systems.superstructure;

public class ShooterGoal {

    public static double idlePosition = 9;
    public static double shooterSteps = 55;
    public static double[] shooterRawPositionBounds = {
        0.0,
        0.0
    };

    public enum PositionGoal {
        TRACKED,
        SPEAKER,
        AMP,
        SOURCE,
        IDLE;

        public double getPosition() {
            switch (this) {
                case AMP: return 19;
                case IDLE: return idlePosition;
                case SOURCE: return 19;
                case SPEAKER: return 17;
                case TRACKED: return 0;
                default: return idlePosition;  
            }
        }

        public double getShooterVelocity() {
            switch (this) {
                case AMP: return 18.0;
                case IDLE: return 0.0;
                case SOURCE: return -100.0;
                case SPEAKER: return 100.0;
                case TRACKED: return 100.0;
                default: return 0.0;
            }
        }
    }
}
