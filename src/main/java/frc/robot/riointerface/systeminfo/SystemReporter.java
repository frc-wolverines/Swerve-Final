package frc.robot.riointerface.systeminfo;

import edu.wpi.first.wpilibj.DriverStation;
import frc.montylib.util.DashboardLog;
import frc.robot.riointerface.Robot;

public class SystemReporter {
    public SystemReporter() {
        new DashboardLog("FMS Connected", DriverStation.isFMSAttached());
        new DashboardLog("ROBOT Enabled", DriverStation.isEnabled());
        new DashboardLog("CONTROLLERS Connected", DriverStation.isJoystickConnected(0) && DriverStation.isJoystickConnected(1));
        new DashboardLog("AUTONOMOUS Ready", Robot.autoHandler.getAutonomousReady());
    }
}
