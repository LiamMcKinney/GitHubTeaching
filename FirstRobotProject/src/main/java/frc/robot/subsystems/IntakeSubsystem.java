/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SimulatedTalon;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  SimulatedTalon intakeMotor1 = new SimulatedTalon("intakeMotor1",6);
  SimulatedTalon intakeMotor2 = new SimulatedTalon("intakeMotor2",7);

    SpeedControllerGroup intake = new SpeedControllerGroup(intakeMotor1, intakeMotor2);

  public IntakeSubsystem() {
    
  }

  public void defaultIntake(){
    intake.set(0.25);
  }

  public void stopIntake(){
    intake.set(0);
  }

  public void setIntakeSpeed(double speed){
    intake.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
