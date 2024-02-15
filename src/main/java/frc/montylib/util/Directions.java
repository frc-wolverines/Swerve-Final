package frc.montylib.util;

public class Directions {

    public enum MotorDirection {
        FORWARD,
        REVERSE;

        public boolean getAsBoolean() {
            return this.equals(FORWARD) ? false : true;
        }

        public double getAsCoefficient() {
            return this.equals(FORWARD) ? 1.0 : -1.0;
        }
    }

    public enum RobotDirection {
        FORWARD,
        REVERSE,
        LEFT,
        RIGHT
    }
}
