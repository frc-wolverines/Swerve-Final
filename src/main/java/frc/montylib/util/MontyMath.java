package frc.montylib.util;

public class MontyMath {
    
    public static double applyValueThreshold(double value, double threshold) {
        return Math.abs(value) > threshold ? value : 0.0;
    }

    public static double valueDifference(double x1, double x2) {
        return x1 > x2 ? x1 - x2 : x2 - x1;
    }
}
