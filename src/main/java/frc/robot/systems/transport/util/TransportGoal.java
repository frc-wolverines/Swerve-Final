package frc.robot.systems.transport.util;

public class TransportGoal {
    
    public static double upPosition = 0.0;
    public static double downPosition = 26.0;

    public enum TransportMode {
        MANUAL,
        PRESET;
    }

    public enum TransportPreset {
        UP,
        DOWN;

        public double getPosition() {
            switch (this) {
                case UP: return upPosition;
                case DOWN: return downPosition;
                default: return upPosition;
            }
        }
    }

}
