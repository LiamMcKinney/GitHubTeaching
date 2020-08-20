/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.Climb;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FeedBalls;
import frc.robot.commands.MoveToIntake;
import frc.robot.commands.MoveToShooter;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.SpinUp;
import frc.robot.commands.Succ;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();

  //Joystick joystick = new Joystick(0);
  CommandLineJoystick test = new CommandLineJoystick(1);

  

  /*XboxController xboxController = new XboxController(1);

  JoystickButton spinUpButton = new JoystickButton(xboxController, 2);
  JoystickButton intakeButton = new JoystickButton(xboxController, 3);
  JoystickButton shootButton = new JoystickButton(xboxController, 4);

  POVButton moveToShooterButton = new POVButton(xboxController, 0);
  POVButton moveToIntakeButton = new POVButton(xboxController, 180);
  POVButton manualIntakeButton = new POVButton(xboxController, 90);*/

  JoystickButton maintainShooterSpeed = new JoystickButton(test, 3);

  SimButton testButton = new SimButton("w");

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Drive m_drive = new Drive(m_driveSubsystem, ()->0 , ()->0);
  



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    // Configure the button bindings
    m_driveSubsystem.setDefaultCommand(m_drive);
    m_shooterSubsystem.setDefaultCommand(
      new RunCommand(
        () -> m_shooterSubsystem.movePID(test.getY()),
        m_shooterSubsystem));

    

    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    /*spinUpButton.toggleWhenActive(new SpinUp(m_shooterSubsystem));
    shootButton.whileHeld(new FeedBalls(m_shooterSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem));
    intakeButton.whileHeld(
      new ConditionalCommand(
        new WaitCommand(0.0000000000000000000000000042069), 
        new Succ(m_hopperSubsystem, m_intakeSubsystem, m_ballTrackingSubsystem), 
        m_ballTrackingSubsystem::isHopperFull)
        );

    moveToShooterButton.whileHeld(new MoveToShooter(m_hopperSubsystem));
    moveToIntakeButton.whileHeld(new MoveToIntake(m_hopperSubsystem));
    manualIntakeButton.whileHeld(new SpinIntake(m_intakeSubsystem));*/


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    /*return new SequentialCommandGroup(
      new ParallelCommandGroup(
        new DriveDistance(20, m_driveSubsystem),
        //would put in subsystem 
        new SwitchLevel(2)), 
      new GyroTurn(90, m_driveSubsystem),
      new DriveDistance(5, m_driveSubsystem),
      //would put in subsystem 
      new DropCube(3));
    */

    return new Climb(m_climbSubsystem, 50);
    
    /*new FunctionalCommand(
      ()->{},
      ()->{m_shooterSubsystem.movePID(m_shooterSubsystem.getTargetSpeed(test.getY()));},
      (end)->{m_shooterSubsystem.stopShoot();}, 
      ()->{return false;}, 
      m_shooterSubsystem); */

    //return new DriveDistance(m_driveSubsystem, 30.0);
    
    //m_shooterSubsystem.getTargetSpeed(m_visionSubsystem.getTargetDistance()));

    
    
    
    /*new ParallelDeadlineGroup(

        new SequentialCommandGroup(
          new VisionTurn(m_driveSubsystem, m_visionSubsystem),
          new FeedBalls(m_shooterSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem),
          new GyroTurn(0, m_driveSubsystem),
          new ParallelDeadlineGroup(
            new DriveDistance(-10 ,m_driveSubsystem),
            new Succ(m_hopperSubsystem, m_intakeSubsystem, m_ballTrackingSubsystem)),
          new GyroTurn(30, m_driveSubsystem),
          new DriveDistance(17, m_driveSubsystem),
          new VisionTurn(m_driveSubsystem, m_visionSubsystem),
          new FeedBalls(m_shooterSubsystem, m_hopperSubsystem, m_ballTrackingSubsystem)),
        
        new SpinUp(m_shooterSubsystem)
    
          );
       */ 
    //1. Spin Continuously
    //2. Turn the robot
    //3. Shoot
    //4. Turn back
    //5. Back and intake
    //6. Turn
    //7. Drive
    //8. Turn
    //9. feed balls
    //10. Shoot

  }

  public class DropCube extends CommandBase{
    public DropCube(double gripperSubsystem){

    }
  }

  public class SwitchLevel extends CommandBase{
    public SwitchLevel(double mastSubsystem){
      
    }
  }

  public class ScaleLevel extends CommandBase{
    public ScaleLevel(double mastSubsystem){
      
    }
  }


}
