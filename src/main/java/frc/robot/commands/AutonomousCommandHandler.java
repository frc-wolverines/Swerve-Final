package frc.robot.commands;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.montylib.util.DashboardLog;

public class AutonomousCommandHandler {
    private SendableChooser<AutonomousRoutine> autonomousChooser = new SendableChooser<AutonomousRoutine>();
    private SendableChooser<Integer> startingPositionChooser = new SendableChooser<Integer>();
    private SendableChooser<Integer> varientChooser = new SendableChooser<Integer>();

    public void configureAutonomousChooser() {
        autonomousChooser.setDefaultOption("No Auto", AutonomousRoutine.NONE);
        autonomousChooser.addOption("Taxi", AutonomousRoutine.TAXI);
        autonomousChooser.addOption("Shoot -> Taxi", AutonomousRoutine.SHOOT_TAXI);
        autonomousChooser.addOption("Two Note", AutonomousRoutine.TWO_NOTE);
        autonomousChooser.addOption("Four Note", AutonomousRoutine.FOUR_NOTE);

        startingPositionChooser.setDefaultOption("No Position", 0);
        startingPositionChooser.addOption("Position 1", 1);
        startingPositionChooser.addOption("Position 2", 2);
        startingPositionChooser.addOption("Position 3", 3);

        varientChooser.setDefaultOption("No Varient", 0);
        varientChooser.addOption("Varient 1", 1);
        varientChooser.addOption("Varient 2", 2);
        varientChooser.addOption("Varient 3", 3);

        new DashboardLog("Autonomous Chooser", autonomousChooser);
        new DashboardLog("Starting Position Chooser", startingPositionChooser);
        new DashboardLog("Varient Chooser", varientChooser);
    }

    public AutonomousRoutine getAutonomousFromChooser() {
        return autonomousChooser.getSelected();
    }

    public int getAutonomousStartingPosition() {
        return startingPositionChooser.getSelected();
    }

    public int getAutonomousVarient() {
        return varientChooser.getSelected();
    }

    public boolean getAutonomousReady() {
        return autonomousChooser.getSelected() != AutonomousRoutine.NONE && 
        startingPositionChooser.getSelected() != 0 && 
        varientChooser.getSelected() != 0;
    }

    public enum AutonomousRoutine {
        NONE,
        TAXI,
        SHOOT_TAXI,
        TWO_NOTE,
        FOUR_NOTE;

        public PathPlannerAuto getAsPathPlannerAuto(int starting_position, int variant) {
            switch (this) {
                case NONE: return null;
                case TAXI: return new PathPlannerAuto("Park " + starting_position + " a" + variant);
                case SHOOT_TAXI: return null;
                case TWO_NOTE: return null;
                case FOUR_NOTE: return null;
                default: return null;
            }
        }
    }
}