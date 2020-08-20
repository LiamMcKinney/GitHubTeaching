/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SimulatedTalon;

public class ClimbSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimbSubsystem.
   */
  SimulatedTalon leftClimberMotor = new SimulatedTalon("left", 19, true);
  SimulatedTalon rightClimberMotor = new SimulatedTalon("right", 20);
  
  public ClimbSubsystem() {

    rightClimberMotor.set(ControlMode.Follower, 19);
    leftClimberMotor.configureMotorConstants(.075, .01, .001, .3);
    rightClimberMotor.configureMotorConstants(.075, .01, .001, .3);

  }

  public void manualClimb(double speed){
    leftClimberMotor.set(speed);
  }

  public double getPosition(){
    return leftClimberMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
