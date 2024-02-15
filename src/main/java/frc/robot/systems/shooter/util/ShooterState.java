package frc.robot.systems.shooter.util;

import edu.wpi.first.math.controller.PIDController;

/**A class that represents the state of the shooter subsystem at a given instance */
public class ShooterState {

    public double top_power, bottom_power, pivot_power;

    public static ShooterState fromPivotPosition(double top_power, double bottom_power, double pivot_desired_position, double pivot_current_position, PIDController controller) {
        return new ShooterState(top_power, bottom_power, controller.calculate(pivot_current_position, pivot_desired_position));
    }

    public static ShooterState fromPivotPosition(ShooterState state, double pivot_desired_position, double pivot_current_position, PIDController controller) {
        return new ShooterState(state.top_power, state.bottom_power, controller.calculate(pivot_current_position, pivot_desired_position));
    }

    public ShooterState(double top_power, double bottom_power, double pivot_power) {

        this.top_power = top_power;
        this.bottom_power = bottom_power;
        this.pivot_power = pivot_power;

    }
}
