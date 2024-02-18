package frc.robot.systems.superstructure;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import frc.montylib.util.LimelightHelper;
import frc.robot.systems.shooter.ShooterConstants;

public class ShooterGoal {

    public enum PositionGoal {
        TRACKED, //Tracked to an apriltag
        SPEAKER, //Position of the shooter when the robot is positioned against the Subwoofer to score in the Speaker
        AMP, //Position of the shooter when the robot is positioned agaist the Amp to score in the Amp
        SOURCE, //Position of the shooter when the robot is positioned against the Source to intake from the Source
        IDLE; //Position of the shooter when it is parellel to the intake and transport system

        /**
         * Gets the position to pivot to in steps (Not applicable to TRACKED)
         * @return a position in shooter steps
         * @see getTrackedPower to return a power to pivot towards a target
         */
        public double getPosition() {
            switch (this) {
                case AMP: return 19;
                case IDLE: return ShooterConstants.kIdlePosition;
                case SOURCE: return 19;
                case SPEAKER: return 17;
                case TRACKED: return 0;
                default: return ShooterConstants.kIdlePosition;  
            }
        }

        /**
         * Returns a power that can be fed directly into the pivot motor causing it to face a camera target
         * @param controller the PIDController to calculate the motor's movement
         * @return a number, between -1 and 1 that will pivot the shooter towards a tracked target
         */
        public double getTrackedPower(PIDController controller) {
            if (this.equals(TRACKED)) {
                return controller.calculate(LimelightHelper.getTY("") * 0.05, 0.0);
            } else {
                return 0.0;
            }
        }

        /**
         * Gets the appropriate target velocity for the shooter for the given ShooterGoal
         * @return a number representing the target velocity of the shooter to get the desired behavior
         */
        public double getShooterVelocity() {
            switch (this) {
                case AMP: return 18.0;
                case IDLE: return 0.0;
                case SOURCE: return -100.0;
                case SPEAKER: return 100.0;
                case TRACKED: return 100.0;
                default: return 0.0;
            }
        }
    }
}
