/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ClimbSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Climb extends PIDCommand {
  /**
   * Creates a new Climb.
   */
  public Climb(ClimbSubsystem m_climbSubsystem, double height) {
    super(
        // The controller that the command will use
        new PIDController(0.2, 0.05, 0.15),
        // This should return the measurement
        m_climbSubsystem::getPosition,
        // This should return the setpoint (can also be a constant)
        height,
        // This uses the output
        output -> {
          m_climbSubsystem.manualClimb(output);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(m_climbSubsystem);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
