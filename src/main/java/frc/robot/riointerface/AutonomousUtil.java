package frc.robot.riointerface;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousUtil {
    public static BooleanSupplier isAllianceRed() {
        return () -> (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == DriverStation.Alliance.Red : false);
    }
}
