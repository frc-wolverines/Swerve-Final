package frc.robot.systems.swerve;

public class ModuleConfig {
    //Controllable Area Network IDs
    public final int drive_id;
    public final int pivot_id;
    public final int can_coder_id;

    //Motor Directions
    public Direction drive_motor_direction = Direction.FORWARD;
    public Direction pivot_motor_direction = Direction.FORWARD;
    public Direction can_coder_direction = Direction.FORWARD;   

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
            Direction drive_direction, 
            Direction pivot_direction, 
            Direction can_coder_direction
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

    /**
     * Configures the module's hardware directions
     * @param drive_direction the desired positive direction of the drive motor
     * @param pivot_direction the desired positive direction of the pivot motor
     * @param can_coder_direction the desired positive direction of the CANcoder absolute encoder
     */
    public void configureDirections(Direction drive_direction, Direction pivot_direction, Direction can_coder_direction) {
        this.drive_motor_direction = drive_direction;
        this.pivot_motor_direction = pivot_direction;
        this.can_coder_direction = can_coder_direction;
    }

    /**An object containing directions to be used for module hardware setup */
    public enum Direction {
        FORWARD,
        REVERSE
    }

    /**
     * Converts a given direction into a boolean
     * @param direction the given direction
     * @return a boolean representing the state of the direction where true is REVERSE, and false is FORWARD
     */
    public static boolean getDirectionAsBoolean(Direction direction) {
        return direction == Direction.REVERSE ? true : false;
    }

    /**
     * Converts a given direction into a modifying integer
     * @param direction the given direction
     * @return an integer representing the state fo the direction where -1 is REVERSE, and +1 is FORWARD
     */
    public static int getDirectionAsInteger(Direction direction) {
        return direction == Direction.REVERSE ? -1 : 1;
    }
}