package frc.montylib.util.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.montylib.util.status.DigitalSensorStatus;

public class BreakBeamSensor extends DigitalInput {
    private DigitalSensorStatus status;

    public BreakBeamSensor(int dio_channel) {
        super(dio_channel);
        status = new DigitalSensorStatus();
    }

    public DigitalSensorStatus isBroken() {
        status.update(get());
        return status;
    }
}
