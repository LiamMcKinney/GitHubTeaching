/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * A button that can be controlled by the command line. You must create a CommandLineJoystick object first.
 */
public class SimButton extends Button{

    String key;

    /**
     * 
     * @param key the string that will trigger the button.
     */
    public SimButton(String key){
        this.key = key;
    }
    
    public boolean get() {
        return CommandLineJoystick.instance.keysPressed.indexOf(key) != -1;
    }
}