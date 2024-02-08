package frc.montylib.util;

public class MontyMath {
    
    public static double applyValueThreshold(double value, double threshold) {
        return Math.abs(value) > threshold ? value : 0.0;
    }

}
