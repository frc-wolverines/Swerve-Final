package frc.robot;

public class DriverUtil {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double kControlAxisThreshold = 0.15;

    public static final double[] kTeleOpSpeeds = {
        5, 7.5, 12
    };

    public static final double[] kTeleOpAngularSpeeds = {
        2.5 * Math.PI, 
        3 * Math.PI,
        5 * Math.PI
    };
}
