package frc.montylib.util.hardware;

import com.revrobotics.CANSparkMax;

public class NEO extends CANSparkMax {
    public NEO(int deviceId) {
        super(deviceId, MotorType.kBrushless);
    }
}
