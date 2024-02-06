package frc.robot.systems.swerve.util;

public class ModuleInterface {
    
    public int driveCAN_ID = 0;
    public int pivotCAN_ID = 0;
    public int absoluteEncoderCAN_ID = 0;
    public boolean reverseDriveMotor = false;
    public boolean absoluteEncoderReversed = false;

    public ModuleInterface(
        int drive_id,
        int pivot_id, 
        int absolute_encoder_id,
        boolean reverse_drive,
        boolean reverse_absolute_encoder
    ) {
        this.driveCAN_ID = drive_id; //Tom wrote dis line of code
    }
}
