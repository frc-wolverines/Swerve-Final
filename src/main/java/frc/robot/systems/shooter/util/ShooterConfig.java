package frc.robot.systems.shooter.util;

import frc.montylib.util.Directions.MotorDirection;

public class ShooterConfig {
    public final int top_shooter_id;
    public final int bottom_shooter_id;
    public final int pivoter_id;
    public final int primer_id;

    public final MotorDirection top_shooter_direction;
    public final MotorDirection bottom_shooter_direction;
    public final MotorDirection pivoter_direction;
    public final MotorDirection primer_direction;

    public ShooterConfig (
            int top_shooter_id, 
            int bottom_shooter_id,
            int pivoter_id,
            int primer_id,
            MotorDirection top_shooter_direction,
            MotorDirection bottom_shooter_direction,
            MotorDirection pivoter_direction,
            MotorDirection primer_direction
        ) {
            this.top_shooter_id = top_shooter_id;
            this.bottom_shooter_id = bottom_shooter_id;
            this.pivoter_id = pivoter_id;
            this.primer_id = primer_id;

            this.top_shooter_direction = top_shooter_direction;
            this.bottom_shooter_direction = bottom_shooter_direction;
            this.pivoter_direction = pivoter_direction;
            this.primer_direction = primer_direction;
    }
}