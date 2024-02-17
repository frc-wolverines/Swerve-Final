package frc.montylib.util;

public class VariableSpeedController {
    public static enum VariableSpeedMode {
        DUAL_SPEED,
        TRI_SPEED
    }

    public static enum TriSpeedCalculateMode {
        INCREASE,
        DECREASE,
        BOTH
    }

    public static enum DualSpeedCalculateMode {
        INCREASE,
        DECREASE
    }

    VariableSpeedMode variableSpeedMode;

    double[] speeds = {};

    boolean enableDualSpeedControl = false;
    boolean enableTriSpeedControl = false;

    public VariableSpeedController(VariableSpeedMode mode) {
        this.variableSpeedMode = mode;
        this.speeds = new double[]{0.0, 0.0, 0.0};

        enableDualSpeedControl = false;
        enableTriSpeedControl = false;
    }

    public void enableTriSpeedControl(double primary_speed, double second_speed, double third_speed) {
        enableTriSpeedControl = true;
        enableDualSpeedControl = false;

        speeds = new double[]{primary_speed, second_speed, third_speed};
    }

    public void enableDualSpeedControl(double primary_speed, double second_speed) {
        enableTriSpeedControl = false;
        enableDualSpeedControl = true;

        speeds = new double[]{primary_speed, second_speed, 0.0};
    }

    public double calculate(DualSpeedCalculateMode calc_mode, double primary_axis) {
        switch (calc_mode) {
            case DECREASE:
                return speeds[0] - (speeds[1] * primary_axis);
            case INCREASE:
                return speeds[0] + (speeds[1] * primary_axis);
            default:
                return speeds[0];
        }
    }

    public double calculate(TriSpeedCalculateMode calc_mode, double primary_axis, double second_axis) {
        switch (calc_mode) {
            case BOTH:
                return 
                speeds[0] + 
                MontyMath.valueDifference(speeds[0], speeds[1]) * primary_axis - 
                MontyMath.valueDifference(speeds[0], speeds[2]) * second_axis;
            case DECREASE:
            return 
                speeds[0] - 
                MontyMath.valueDifference(speeds[0], speeds[1]) * primary_axis - 
                MontyMath.valueDifference(speeds[0], speeds[2]) * second_axis;
                case INCREASE:
            return 
                speeds[0] + 
                MontyMath.valueDifference(speeds[0], speeds[1]) * primary_axis + 
                MontyMath.valueDifference(speeds[0], speeds[2]) * second_axis;
            default:
                return speeds[0];
        }
    }
}
