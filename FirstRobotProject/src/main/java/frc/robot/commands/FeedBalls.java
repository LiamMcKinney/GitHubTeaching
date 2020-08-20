/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class FeedBalls extends CommandBase {
  /**
   * Creates a new Shoot.
   */

  HopperSubsystem hopper;
  ShooterSubsystem shooter;
  BallTrackingSubsystem ballTracking;

  public FeedBalls(ShooterSubsystem shooter, HopperSubsystem hopper, BallTrackingSubsystem ballTracking) {
    
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.hopper = hopper;
    this.ballTracking = ballTracking;

    addRequirements(hopper, ballTracking);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (shooter.isAtSpeed() || !ballTracking.isAtTop()){
      hopper.moveToShooter();
    } 
    else {
      hopper.stopHopper();
      
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.stopHopper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
