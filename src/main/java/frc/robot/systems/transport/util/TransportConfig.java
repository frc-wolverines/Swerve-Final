package frc.robot.systems.transport.util;

import frc.montylib.util.Directions.MotorDirection;

public class TransportConfig {

    public final int intake_id;
    public final int belt_id;
    /**0 is left, and 1 is right */
    public final int[] pivot_ids;

    public final MotorDirection intake_direction;
    public final MotorDirection belt_direction;
    public final MotorDirection[] pivot_directions;

    public TransportConfig(
            int intake_id, int belt_id, int pivot_left_id, int pivot_right_id, 
            MotorDirection intake_direction, MotorDirection belt_direction, 
            MotorDirection pivot_left_direction, MotorDirection pivot_right_direction
        ) {
            this.intake_id = intake_id;
            this.belt_id = belt_id;
            this.pivot_ids = new int[] {pivot_left_id, pivot_right_id};

            this.intake_direction = intake_direction;
            this.belt_direction = belt_direction;
            this.pivot_directions = new MotorDirection[] {
                pivot_left_direction,
                pivot_right_direction
            };
    }
}
