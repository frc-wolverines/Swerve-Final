package frc.montylib.util.hardware;

import com.revrobotics.CANSparkMax;

public class NEOv1 extends CANSparkMax {
    public NEOv1(int deviceId) {
        super(deviceId, MotorType.kBrushless);
    }
}
