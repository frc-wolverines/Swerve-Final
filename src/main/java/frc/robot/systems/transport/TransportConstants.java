package frc.robot.systems.transport;

import frc.montylib.util.PIDConstants;
import frc.montylib.util.Directions.MotorDirection;
import frc.robot.systems.transport.util.TransportConfig;

public class TransportConstants {
    
    public static double kPositionMin = 0.0;
    public static double kPositionMax = 0.0;

    public static PIDConstants kPivotConstants = new PIDConstants(0.15 / 30, 0.0, 0.0);

    public static TransportConfig kTransportConfig = new TransportConfig(
        18, 
        19, 
        20, 
        21, 
        MotorDirection.FORWARD, 
        MotorDirection.FORWARD, 
        MotorDirection.REVERSE, 
        MotorDirection.FORWARD
    );

}
