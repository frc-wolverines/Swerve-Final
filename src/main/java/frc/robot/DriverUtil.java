package frc.robot;

public class DriverUtil {
    //User input constants
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double kControlAxisThreshold = 0.15;

    //The stepped speeds that will be used in multivariable speed mode
    public static final double[] kTeleOpSpeeds = {
        5, 7.5, 12
    };

    //The stepped angular speeds that will be used in multivariable speed mode
    public static final double[] kTeleOpAngularSpeeds = {
        2.5 * Math.PI, 
        3 * Math.PI,
        5 * Math.PI
    };
}
