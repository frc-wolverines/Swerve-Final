package frc.montylib.kinematics;

/**A class that represents the state of the ShooterPod in any given moment */
public class ShooterPosition {
    public double vt_rotations_per_second;
    public double vb_rotations_per_second;
    public double vp_rotations_per_second;
    public double pivot_position_percentage;

    /**
     * Constructs a new instance of ShooterPosition
     * @param vtop the velocity of the top shooter wheels in Rotations per Second
     * @param vbottom the velocity of the bottom shooter wheels in Rotations per Second
     * @param vprimer the power of the primer motor in decimal percentage
     * @param pivot_position the power of the pivot motor in decimal percentage
     */
    public ShooterPosition(double vtop, double vbottom, double vprimer, double pivot_position) {
        this.vt_rotations_per_second = vtop;
        this.vb_rotations_per_second = vbottom;
        this.vp_rotations_per_second = vprimer;
        this.pivot_position_percentage = pivot_position;
    }
}
