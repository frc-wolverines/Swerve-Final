package frc.robot.systems.swerve.util;

import frc.montylib.util.Directions.MotorDirection;

public class ModuleConfig {
    //Controllable Area Network IDs
    public final int drive_id;
    public final int pivot_id;
    public final int can_coder_id;

    //Motor Directions
    public MotorDirection drive_motor_direction = MotorDirection.FORWARD;
    public MotorDirection pivot_motor_direction = MotorDirection.FORWARD;
    public MotorDirection can_coder_direction = MotorDirection.FORWARD;   

    /**
     * Constructs a new instance of ModuleConfig
     * @param drive_id the CAN-ID for the drive motor controller
     * @param pivot_id the CAN-ID for the pivot motor controller
     * @param can_coder_id the CAN-ID for the CANcoder absolute encoder
     */
    public ModuleConfig(int drive_id, int pivot_id, int can_coder_id) {

        //Controllable Area Network IDs
        this.drive_id = drive_id;
        this.pivot_id = pivot_id;
        this.can_coder_id = can_coder_id;
    }

    /**
     * Constructs a new instance of ModuleConfig
     * @param drive_id the CAN-ID for the drive motor controller
     * @param pivot_id the CAN-ID for the pivot motor controller 
     * @param can_coder_id the CAN-ID for the CANcoder absolute encoder
     * @param drive_direction the desired direction of the drive motor 
     * @param pivot_direction the desired direction of the pivot motor
     * @param can_coder_direction the desired direction of the CANcoder absolute encoder
     */
    public ModuleConfig(
            int drive_id, 
            int pivot_id, 
            int can_coder_id, 
            MotorDirection drive_direction, 
            MotorDirection pivot_direction, 
            MotorDirection can_coder_direction
        ) {
            //Controllable Area Network IDs
            this.drive_id = drive_id;
            this.pivot_id = pivot_id;
            this.can_coder_id = can_coder_id;

            //Directions
            this.drive_motor_direction = drive_direction;
            this.pivot_motor_direction = pivot_direction;
            this.can_coder_direction = can_coder_direction;
    }
}