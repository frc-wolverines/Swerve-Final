package frc.montylib.kinematics;

public class TransportState {
    public double vi_rotations_per_second;
    public double vb_rotations_per_second;
    public double intake_power_percentage;

    /**
     * Constructs a new instance of TransportState
     * @param vintake the velocity of the intake wheels in Rotations per Second
     * @param vbelts the velocity of the transport belts and rollers in Rotations per Second
     * @param pivot_power the angle of the intake arm in decimal percentage
     */
    public TransportState(double vintake, double vbelts, double pivot_power) {
        this.vi_rotations_per_second = vintake;
        this.vb_rotations_per_second = vbelts;
        this.intake_power_percentage = pivot_power;
    }
}
