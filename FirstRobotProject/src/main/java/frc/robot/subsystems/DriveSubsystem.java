/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SimulatedGyro;
import frc.robot.SimulatedTalon;

import static frc.robot.Constants.DriveConstants;


//VROOM VROOM

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  //declaration of new variables in WPI_Talon/etc.
  SimulatedTalon rightBack = new SimulatedTalon("writebacc",DriveConstants.rightBackID);
  SimulatedTalon rightFront = new SimulatedTalon("writefrounght",DriveConstants.rightFrontID);
  SimulatedTalon leftBack = new SimulatedTalon("lephtbacc",DriveConstants.leftBackID);
  SimulatedTalon leftFront = new SimulatedTalon("lephtfrounght",DriveConstants.leftFrontID);

  SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftBack, leftFront);
  SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightBack, rightFront);

  DifferentialDrive diffDrive = new DifferentialDrive(leftGroup, rightGroup);

  SimulatedGyro simulatedGyro = new SimulatedGyro(leftFront, rightFront, 3);

  public DriveSubsystem() {

  }

  

  @Override
  public void periodic() {
    simulatedGyro.updateAngle(.02);
    // This method will be called once per scheduler run
  }

  //drive method that uses DifferentialDrive
  public void drive(double forward, double turn){

    diffDrive.arcadeDrive(forward, turn);


  }

  public double getAngle(){

    return simulatedGyro.getAngle();

  }

  public void resetGyro(){

    simulatedGyro.resetAngle();
    
  }

  public double getDistance(){

    return DriveConstants.feetPerTick * (leftFront.getSelectedSensorPosition() - rightFront.getSelectedSensorPosition())/2.0;
    
  }

  public void resetEncoders(){

    rightFront.setSelectedSensorPosition(0);
    leftFront.setSelectedSensorPosition(0);

  }

}

