package frc.montylib.kinematics;

public class TransportState {
    public double vi_rotations_per_second;
    public double vb_rotations_per_second;
    public double intake_angle;

    /**
     * Constructs a new instance of TransportState
     * @param vintake the velocity of the intake wheels in Rotations per Second
     * @param vbelts the velocity of the transport belts and rollers in Rotations per Second
     * @param intake_angle the angle of the intake arm in Radians
     */
    public TransportState(double vintake, double vbelts, double intake_angle) {
        this.vi_rotations_per_second = vintake;
        this.vb_rotations_per_second = vbelts;
        this.intake_angle = intake_angle;
    }
}
