package frc.montylib.util.hardware;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;

public class NavX2 extends AHRS {
    public NavX2() {
        super(Port.kMXP);
    }
}
