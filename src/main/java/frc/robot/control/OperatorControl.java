/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Made by Ollie 1/25/19
 */
public class OperatorControl extends JoystickControl {

    UsbCamera camera;

    Thread camera1;
    Thread camera2;

    public OperatorControl() {
        super(1);
    }

    /**
     * Process any specific events for button 1 on the joystick.
     * 
     * @param robot
     */
    protected void processButton1(Robot robot) {
        /*
         * if(robot.) new Thread(() -> { camera =
         * CameraServer.getInstance().startAutomaticCapture(0);
         * camera.setResolution(256, 144); camera.setFPS(20); }).start();
         */
    }

    /**
     * Process any specific events for button 2 on the joystick.
     * 
     * @param robot
     */
    protected void processButton2(Robot robot) {

        new Thread(() -> {
            camera = CameraServer.getInstance().startAutomaticCapture(1);
            camera.setResolution(256, 144);
            camera.setFPS(20);
        }).start();

    }

    /**
     * Process any specific events for button 3 on the joystick.
     * 
     * @param robot
     */
    protected void processButton3(Robot robot) {
    }

    /**
     * Process any specific events for button 4 on the joystick.
     * 
     * @param robot
     */
    protected void processButton4(Robot robot) {
    }

    /**
     * Process any specific events for button 5 on the joystick.
     * 
     * @param robot
     */
    protected void processButton5(Robot robot) {
        // This is the opportunity for a subclass to create button five
    }

    /**
     * Process any specific events for button 6 on the joystick.
     * 
     * @param robot
     */
    protected void processButton6(Robot robot) {
        // This is the opportunity for a subclass to create button six
    }

    /**
     * Process any specific events for button 7 on the joystick.
     * 
     * @param robot
     */
    protected void processButton7(Robot robot) {
        robot.getLift().liftUp(1);
        SmartDashboard.putString("Lift Direction: ", "up");
    }

    /**
     * Process any specific events for button 8 on the joystick.
     * 
     * @param robot
     */
    protected void processButton8(Robot robot) {
        robot.getLift().liftDown(1);
        SmartDashboard.putString("Lift Direction: ", "down");
    }

    /**
     * Process any specific events for button 9 on the joystick.
     * 
     * @param robot
     */
    protected void processButton9(Robot robot) {
        robot.getBall_intake().intakeUp(.4);
    }

    /**
     * Process any specific events for button 10 on the joystick.
     * 
     * @param robot
     */
    protected void processButton10(Robot robot) {
        robot.getBall_intake().intakeDown(.3);
    }

    /**
     * Process any specific events for button 11 on the joystick.
     * 
     * @param robot
     */
    protected void processButton11(Robot robot) {

    }

    /**
     * Process any specific events for button 12 on the joystick.
     * 
     * @param robot
     */
    protected void processButton12(Robot robot) {
    }

    /**
     * Process any specific events for no buttons on the joystick.
     * 
     * @param robot
     */
    protected void processNoButtons(Robot robot) {
        // This is the opportunity for a subclass to create no button
        robot.getBall_intake().intakeSuckIn(0);
        robot.getBall_intake().intakeUp(0);
        robot.getCarridge().takeIn(0);
        robot.getLift().liftUp(0);
        robot.getLift().liftDown(0);
        SmartDashboard.putString("Lift Direction: ", "Stopped");
    }
}
