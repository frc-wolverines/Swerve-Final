package frc.robot;

import edu.wpi.first.math.util.Units;

public class DriverConstants {
    //User input constants
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double kControlAxisThreshold = 0.15;

    //The stepped speeds that will be used in multivariable speed mode
    public static final double[] kTeleOpSpeeds = {
        Units.feetToMeters(5), Units.feetToMeters(7.5), Units.feetToMeters(12)
    };

    //The stepped angular speeds that will be used in multivariable speed mode
    public static final double[] kTeleOpAngularSpeeds = {
        0.75, 
        1.25,
        2
    };
}
