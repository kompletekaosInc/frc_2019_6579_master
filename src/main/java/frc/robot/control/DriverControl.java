/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystem.Drivetrain;

/**
 * Created by Ollie 1/25/19
 */
public class DriverControl extends JoystickControl {
    
    //Driver will be using joystick from port 0
    public DriverControl(){
        super(0);
    }

    private void arcadeDrive(Drivetrain drivetrain){
        double throttleValue = (stick.getThrottle()-1)/2;

        double newX = stick.getX() * throttleValue;
        double newY = stick.getY() * throttleValue;

        SmartDashboard.putNumber("Throttle Value: ", throttleValue);
        SmartDashboard.putNumber("Stick X", stick.getX());
        SmartDashboard.putNumber("Stick Y", stick.getY());

        try{
        drivetrain.arcadeDiffDrive(newX, newY);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void giveCommands(Robot robot){
        super.giveCommands(robot);

        arcadeDrive(robot.getDrivetrain());
    }
    
    protected void processButton1(Robot robot ){
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 1);
    }

    protected void processButton2(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 2);
    }
    protected void processButton3(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 3);
    }
    protected void processButton4(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 4);
    }
    protected void processButton5(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 5);
    }
    protected void processButton6(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 6);
    }
    protected void processButton7(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 7);
    }
    protected void processButton8(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 8);

    }
    protected void processButton9(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 9);
    }
    protected void processButton10(Robot robot ) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 10);

    }
    protected void processButton11(Robot robot) {
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 11);

    }
    protected void processButton12(Robot robot ){
        SmartDashboard.putNumber("Button Being Pressed (Driver)", 12);
    }
    @Override
    protected void processNoButtons(Robot robot ) {
        //robot.getLift().stop();
        SmartDashboard.putNumber("Throttle",stick.getThrottle());
        SmartDashboard.putNumber("AdjustedThrottle",((stick.getThrottle()-1)/-2));
        System.out.println("driver no buttons");

    }

}
