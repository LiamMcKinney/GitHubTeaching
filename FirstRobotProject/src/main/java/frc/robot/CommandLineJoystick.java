/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import java.util.Scanner;

/**
 * A way to control the simulation robot from the terminal (the thing at the bottom of the VSCode window.)
 * <p>once you make the joystick, you can control it by typing in commands such as "y=.5" or "x=-1" to set the joystick axes,
 *  or "press a" or "release 4" to press and release buttons. You can access those buttons with the SimButton class.
 */
public class CommandLineJoystick extends GenericHID {

    public static CommandLineJoystick instance;

    Scanner reader = new Scanner(System.in);

    String keysPressed = "";
    double y = 0;
    double x = 0;

    public CommandLineJoystick(int port) {
        super(port);
        instance = this;
        new Thread(() -> {
            while(true){
                updateInput();
            }
        }).start();
    }

    @Override
    public double getX(Hand hand) {
        return x;
    }

    @Override
    public double getY(Hand hand) {
        return y;
    }

    void updateInput(){
        String input = reader.nextLine();
        if(input.startsWith("y=") || input.startsWith("y = ")){
            y = Double.parseDouble(input.replaceAll("y ?= ?", ""));
        }else if(input.startsWith("x=") || input.startsWith("x = ")){
            x = Double.parseDouble(input.replaceAll("x ?= ?", ""));
        }else if(input.startsWith("press ")){
            keysPressed += input.replaceAll("press ", "");
        }else if(input.startsWith("release ")){
            keysPressed = keysPressed.replaceAll("["+input.replaceAll("release ", "")+"]", "");
        }
    }
}