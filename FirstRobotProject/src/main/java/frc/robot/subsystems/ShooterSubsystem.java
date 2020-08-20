/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.TreeMap;
import java.util.Map.Entry;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SimulatedTalon;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  SimulatedTalon shooter1 = new SimulatedTalon("shooter1", 8, true);
  SimulatedTalon shooter2 = new SimulatedTalon("shooter2", 9);

  TreeMap<Double, Double> distanceSpeedMap = new TreeMap<>();

  public ShooterSubsystem() {

    shooter2.set(ControlMode.Follower, 8);

    shooter1.selectProfileSlot(0, 0);
    // shooter2.setInverted(InvertType.OpposeMaster);

    distanceSpeedMap.put(3.69, -1.0); // Some other number I deleted whoops
    distanceSpeedMap.put(4.17, -20.0); //-31450.0);
    distanceSpeedMap.put(4.98, -40.0); //-32500.0);
    distanceSpeedMap.put(5.59, -60.0); //-33000.0);
    distanceSpeedMap.put(6.00, -89.0); //-33400.0);

    shooter1.config_kP(8, Constants.ShooterPIDConstants.kP);
    shooter1.config_kI(8, Constants.ShooterPIDConstants.kI);
    shooter1.config_kD(8, Constants.ShooterPIDConstants.kD);
    shooter1.config_kF(8, Constants.ShooterPIDConstants.kF);
    shooter1.configMaxIntegralAccumulator(8, Constants.ShooterPIDConstants.kIMax);

    shooter2.set(ControlMode.Follower, 8);
  }

  public void defaultShoot() {
    shooter1.set(0.25);
  }

  public void stopShoot() {
    shooter1.set(0);
  }

  // for testing/getting the right shoot speed
  public void manualShoot(double speed) {
    shooter1.set(speed);
  }

  public void movePID(double STUsPerDeciecond) {
    shooter1.set(ControlMode.Velocity, STUsPerDeciecond);
  }

  public boolean isAtSpeed() {
    return true;
  }

  public double getTargetSpeed(double distance) {

    if(distance < distanceSpeedMap.firstKey()){
      return distanceSpeedMap.get(distanceSpeedMap.firstKey());
    }
    else if(distance > distanceSpeedMap.lastKey()){
      return distanceSpeedMap.get(distanceSpeedMap.lastKey());
    }

    double ceiling = distanceSpeedMap.ceilingKey(distance);
    double floor = distanceSpeedMap.floorKey(distance);

    if(ceiling == floor){
      return distanceSpeedMap.get(ceiling);
    }

    double proportion = (distance - floor) / (ceiling - floor);

    double ceilingSpeed = distanceSpeedMap.get(ceiling);
    double floorSpeed = distanceSpeedMap.get(floor);

    return ((ceilingSpeed - floorSpeed) * proportion) + floorSpeed;
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
