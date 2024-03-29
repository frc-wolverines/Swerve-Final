package frc.montylib.util.hardware;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;

import frc.montylib.util.PIDConstants;

public class NEO extends CANSparkMax {

    public double actuationGearRatio = 0.0;
    public PIDController velocityController = new PIDController(0.0, 0.0, 0.0);
    public PIDController positionController = new PIDController(0.0, 0.0, 0.0);

    public NEO(int deviceId) {
        super(deviceId, MotorType.kBrushless);
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
        return this.getEncoder().getVelocity() / 60 * actuationGearRatio;
    }

    public double getActuatedPosition() {
        return this.getEncoder().getPosition() * actuationGearRatio;
    }

    public void runToVelocity(double velocity) {
        this.set(velocityController.calculate(this.getEncoder().getVelocity() / 60, velocity));
    }

    public void runToPosition(double position) {
        this.set(positionController.calculate(this.getEncoder().getPosition(), position));
    }

    public void runToActuateVelocity(double velocity) {
        this.set(velocityController.calculate(this.getActuatedVelocity(), velocity));
    }

    public void runToActuatePosition(double position) {
        this.set(positionController.calculate(this.getActuatedPosition(), position));
    }
}
