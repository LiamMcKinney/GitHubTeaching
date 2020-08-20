/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveDistance extends PIDCommand {
  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(DriveSubsystem m_driveSubsystem, double distance) {
    super(
        // The controller that the command will use
        // kP,kI,kD
        new PIDController(0.5, 0, 0.2),
        // This should return the measurement
        m_driveSubsystem::getDistance,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          // Use the output here
          m_driveSubsystem.drive(output, 0);

        });
        
        addRequirements(m_driveSubsystem);
        m_driveSubsystem.resetEncoders();
        getController().setTolerance(0.1, .1);
        getController().setIntegratorRange(-.5, 0.5);

    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    
  }
  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return getController().atSetpoint();

  }

}
