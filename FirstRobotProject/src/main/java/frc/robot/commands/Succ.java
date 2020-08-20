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
import frc.robot.subsystems.IntakeSubsystem;

public class Succ extends CommandBase {
  /**
   * Creates a new Succ.
   */

  HopperSubsystem hopper;
  IntakeSubsystem intake;
  BallTrackingSubsystem ballTracking;

  int loopsSinceClear;
  int loopsBeforeStopHopper;

  public Succ(HopperSubsystem hopper, IntakeSubsystem intake, BallTrackingSubsystem ballTracking) {

    // Use addRequirements() here to declare subsystem dependencies.
    this.hopper = hopper;
    this.intake = intake;
    this.ballTracking = ballTracking;
    addRequirements(hopper, intake, ballTracking);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    intake.defaultIntake();

    if(!ballTracking.isAtBottom()){
      loopsSinceClear++;
    } else {
      loopsSinceClear = 0;
    }

    if(loopsSinceClear <= loopsBeforeStopHopper){

      hopper.moveToShooter();

    } else {
      
      hopper.stopHopper();

    }
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    hopper.stopHopper();
    intake.stopIntake();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ballTracking.isAtTop();
  }
}
