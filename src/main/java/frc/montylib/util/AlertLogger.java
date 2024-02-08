package frc.montylib.util;

import edu.wpi.first.wpilibj.DriverStation;

public class AlertLogger {
    public enum StringAlertType {
        INFO,
        WARNING,
        ERROR,
        CRITICAL_ERROR,
        FAULT
    }

    public AlertLogger(String alert_message, StringAlertType type) {
        switch (type) {
            case CRITICAL_ERROR:
                DriverStation.reportError("[CRITICAL ERROR] " + alert_message, false);;
            case ERROR:
                DriverStation.reportError("[ERROR] " + alert_message, false);
            case INFO:
                System.out.println("[INFO] " + alert_message);
            case WARNING:
                DriverStation.reportWarning("[WARNING] " + alert_message, false);
            case FAULT:
                DriverStation.reportWarning("[FAULT] " + alert_message, false);
            default:
                return;
        }
    }
}
