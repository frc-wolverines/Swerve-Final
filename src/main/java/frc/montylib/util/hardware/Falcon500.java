package frc.montylib.util.hardware;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import frc.montylib.util.PIDConstants;

public class Falcon500 extends TalonFX {

    public double actuationGearRatio = 0.0;
    public PIDController velocityController = new PIDController(0.0, 0.0, 0.0);
    public PIDController positionController = new PIDController(0.0, 0.0, 0.0);

    public Falcon500(int deviceId) {
        super(deviceId);
    }

    public void setActuationGearRatio(double ratio) {
        this.actuationGearRatio = ratio;
    }

    public void setVelocityPIDConstants(PIDConstants constants) {
        velocityController = constants.toPIDController();
    }

    public void setPositionPIDConstants(PIDConstants constants) {
        positionController = constants.toPIDController();
    }

    public double getActuatedVelocity() {
        return this.getVelocity().getValueAsDouble() * actuationGearRatio;
    }

    public double getActuatedPosition() {
        return this.getPosition().getValueAsDouble() * actuationGearRatio;
    }

    public void runToVelocity(double velocity) {
        this.set(velocityController.calculate(this.getVelocity().getValueAsDouble(), velocity));
    }

    public void runToPosition(double position) {
        this.set(positionController.calculate(this.getPosition().getValueAsDouble(), position));
    }

    public void runToActuateVelocity(double velocity) {
        this.set(velocityController.calculate(this.getActuatedVelocity(), velocity));
    }

    public void runToActuatePosition(double position) {
        this.set(positionController.calculate(this.getActuatedPosition(), position));
    }
}
