package frc.montylib.util.status;

public class DigitalSensorStatus {
    private boolean raw_status;
    private boolean previous_raw_status;
    private boolean has_change_occured;

    public DigitalSensorStatus() {
        raw_status = false;
        previous_raw_status = false;
        has_change_occured = false;
    }

    public void update(boolean value) {
        previous_raw_status = raw_status;
        raw_status = value;
        if (previous_raw_status != raw_status) {
            has_change_occured = true;
        }
    }

    public boolean getValueAsBoolean() {
        return raw_status;
    }

    public boolean getPreviousValueAsBoolean() {
        return previous_raw_status;
    }

    public boolean hasChanged() {
        return has_change_occured;
    }

    public int getValueAsInteger() {
        return raw_status == false ? 0 : 1;
    }
}
