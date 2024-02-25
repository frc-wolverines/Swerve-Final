package frc.montylib.util;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;

public class PositionController {

    /**
     * Converts a target position to a power value based on configured values
     * @param target_position the target position 
     * @return a power value related to the position delta in decimal percentage
     */
    public double calculate(double target_position) {
        return controller.calculate(feedbackSupplier.get(), target_position);
    }

    private PIDController controller;
    private Supplier<Double> feedbackSupplier;

    /**
     * Construcs a new instance of PositionController
     * @param constants the PIDConstants for an integrated PIDController
     * @param current_position_supplier a function to return the current position of the mechanism
     */
    public PositionController(PIDConstants constants, Supplier<Double> current_position_supplier) {
        this.controller = constants.toPIDController();
        this.feedbackSupplier = current_position_supplier;
    }
}
