package frc.montylib.util;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;

public class PositionController {

    public double toPowerPercantage(double target_position) {
        return controller.calculate(feedbackSupplier.get(), target_position);
    }

    private PIDController controller;
    private Supplier<Double> feedbackSupplier;

    public PositionController(PIDConstants constants, Supplier<Double> current_position_supplier) {
        this.controller = constants.toPIDController();
        this.feedbackSupplier = current_position_supplier;
    }
}
