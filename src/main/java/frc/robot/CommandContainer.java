package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.montylib.util.AlertLogger;
import frc.montylib.util.AlertLogger.StringAlertType;

public class CommandContainer {
  public CommandXboxController xboxController = new CommandXboxController(DriverUtil.kDriverControllerPort);

  public CommandContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    new AlertLogger("Could not verify integrity of configured autonomous command", StringAlertType.FAULT);
    return Commands.none();
  }
}
