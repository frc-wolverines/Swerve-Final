package frc.montylib.util;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardLog {
    
    public DashboardLog(String name, String[] values) {
        SmartDashboard.putStringArray(name, values);
    }

    public DashboardLog(String name, String value) {
        SmartDashboard.putString(name, value);
    }

    public DashboardLog(String name, double[] values) {
        SmartDashboard.putNumberArray(name, values);
    }

    public DashboardLog(String name, double value) {
        SmartDashboard.putNumber(name, value);
    }

    public DashboardLog(String name, Boolean[] values) {
        SmartDashboard.putBooleanArray(name, values);
    }

    public DashboardLog(String name, Boolean value) {
        SmartDashboard.putBoolean(name, value);
    }

    public DashboardLog(String name, Sendable value) {
        SmartDashboard.putData(name, value);
    }
}
