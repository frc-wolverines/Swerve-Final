package frc.montylib.kinematics;

/**A class that represents the state of the Shooter in any given moment */
public class ShooterState {
    public double vt_rotations_per_second;
    public double vb_rotations_per_second;
    public double vp_rotations_per_second;
    public double pivot_power_percentage;

    /**
     * Constructs a new instance of ShooterState
     * @param vshooter the power of the top shooter wheels in Rotations per Second
     * @param vbottom the power of the bottom shooter wheels in Rotations per Second
     * @param vprimer the power of the primer motor in decimal percentage
     * @param pivot_power the power of the pivot motor in decimal percentage
     */
    public ShooterState(double vshooter, double vprimer, double pivot_power) {
        this.vt_rotations_per_second = vshooter;
        this.vb_rotations_per_second = vshooter;
        this.vp_rotations_per_second = vprimer;
        this.pivot_power_percentage = pivot_power;
    }
}
