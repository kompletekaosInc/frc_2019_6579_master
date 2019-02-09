/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import java.util.logging.Logger;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Ollie 1/25/19
 */
public class Drivetrain implements SubSystem {

  //logger
  private Logger logger = Logger.getLogger(this.getClass().getName());

  

  //sensors
  private ADXRS450_Gyro mr_gyro = null;


  //Motor Controllers (ports can be changed)
  private VictorSP leftTB1 = new VictorSP(0);
  private VictorSP leftTB2 = new VictorSP(1);
  private SpeedControllerGroup leftTB = new SpeedControllerGroup(leftTB1, leftTB2);

  private VictorSP rightTB1 = new VictorSP(2);
  private VictorSP rightTB2 = new VictorSP(3);
  private SpeedControllerGroup rightTB = new SpeedControllerGroup(rightTB1, rightTB2);

  //init the drivetrain
  private DifferentialDrive robotDrive = new DifferentialDrive(leftTB, rightTB);

  //neccasary numbers
 // private double distancePerPulse = 0.2493639169;
  public boolean inMotion = false;

  public Drivetrain(){

    new Thread(() -> {
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(256, 144);
      camera.setFPS(20);
  }).start();
    //gets the sensors ready to work
    //camera
    try{
     
    } catch(Exception e){
      logger.info("Camera not installed correctly, someone stuffed up. Robot: 'not my fault :)'" + e.toString());
      Shuffleboard.getTab("Debugging").add("Camera happy and awake", false);
    }

    
    //gyro
    try {
      mr_gyro = new ADXRS450_Gyro();
      Shuffleboard.getTab("Debugging").add("Gyro happy and on", true);
      //callibrates the gyro to 0
      mr_gyro.reset();
      mr_gyro.calibrate();
    } catch (Exception e) {
      logger.info("Gyro not installed correctly, someone stuffed up. Robot: 'not my fault :)'");
      Shuffleboard.getTab("Debugging").add("Gyro happy and on", false);
    }

  };

  

 /* this is the method for moving the robot, could be used by arcade drive and auto
  * @param leftPower
  * @param rightPower
  */
  public void setPower(double leftPower, double rightPower){
    //sets the left toughbox
    leftTB.set(-leftPower);
    //sets the right toughbox
    rightTB.set(rightPower);
    SmartDashboard.putNumber("Power", (rightPower+leftPower)/2);
  }

  /**
   * This method is to stop power in the robot
   * Not a hard stop unless brake is on the motors (hard stop is underneath)
   */
  public void stop(){
    setPower(0, 0);
    Shuffleboard.getTab("Debugging").add("Stopping", true);
  }

  /**
   * Congrats you found hard stop :)
   * This method hardstops the robot
   */

  public void hardStop(){

//    double gyroAtStop = mr_gyro.getAngle();

    logger.info("hardstop: initial (leftToughbox:Right) = (" + leftTB.get() + ":" + rightTB.get() + ")");
    double leftStopPower;
    double rightStopPower;

    //begin wierdness for the hardstop code
    //left toughbox
    if(leftTB.get()>0){
      leftStopPower = -0.1;
    } else {
      leftStopPower = 0.1;
    }
    //right toughbox
    if(rightTB.get()>0){
      rightStopPower = -0.1;
    } else {
      rightStopPower = 0.1;
    }

    //in last years code (2018) Jiah spelt timed timne... is this some wierd convention i dont know, or was it a mistake?
    double beginTimedHardStop = System.currentTimeMillis();
    while(System.currentTimeMillis()-beginTimedHardStop < 25){

      setPower(leftStopPower, rightStopPower);
      logger.fine("hardStop:current (leftToughbox:right) = (" + leftTB.get() + ":" + rightTB.get() + ")");
    }
    stop();
    logger.info("hardStop:end (leftToughbox:right) = (" + leftTB.get() + ":" + rightTB.get() + ")");
    logger.info("hardStop finished, but was it successful...");
  }

  //all gyro methods

  public double getGyroAngle(){
    //publish stats
    double gyroAngle = 0;

    //error handling if gyro has teleported away
    if(mr_gyro != null){
      gyroAngle = mr_gyro.getAngle();
    }
    return gyroAngle;
  }
    /**
     * Gets the moderated gyro angle (makes values between -360 and 360)
     * @return
     */
  public double getModGyroAngle(){
    double gyroModAngle = getGyroAngle()%360;
    SmartDashboard.putNumber("getModAngleValue", gyroModAngle);

    return gyroModAngle;
  }
  public void resetGyro(){
    try{
      mr_gyro.reset();
    } catch (Exception e){
      e.getStackTrace();
    }
  }

  public void followGyro(double power, double gyroTarget){
    //proportionally drives in the direction of a gyro heading, turning to face the right direction
    double currentGyroAngle = getGyroAngle()%360;
    double gyroPowerAdjustment = 0;
    double gyroGain = 0.05;

    //Calculates how much to turn based on the current heading and the target heading
    gyroPowerAdjustment = currentGyroAngle - (gyroTarget%360);
    gyroPowerAdjustment = gyroPowerAdjustment * gyroGain;

    double gyroMotorPowerLeft = -power - gyroPowerAdjustment;
    double gyroMotorPowerRight = power - gyroPowerAdjustment;
    //force the unwilling motors to move, against there super-lazy will
    leftTB.set(gyroMotorPowerLeft);
    rightTB.set(gyroMotorPowerRight);
  }

   //finished gyro code

   /**
     * Performs a curve turn with the robot to the left (if left is true to the target angle.
     * An error handler is implemented to interupt the turn after a reasonable amount of time
     * has elapsed.
     *
     * @param targetAngle
     * @param left
     */
  /*  public void curveTurn(double targetAngle, boolean left){
      logger.info("curveTurn [" + targetAngle + ":" + left + "]");
      double turnPower = 0.3;

      SmartDashboard.putNumber("Mr. Curveturns target angle is: ", targetAngle);
      SmartDashboard.putBoolean("Mr. Curveturn going left?" , left);

      //reset mr. gyro
      mr_gyro.reset(); // do not calibrate as this cause the apocalypse :(
      while(Math.abs(getGyroAngle()) <  (Math.abs(targetAngle))){
        if(left){
          setPower(turnPower, 0);
        } else {
          //must want to turn right
          setPower(0, turnPower);
        }
      }

      logger.info("Curve turn success");
    }

    public void turn(double turnAngle, boolean left){
      logger.info("turn [" + turnAngle + ":" + left + "]");
        double turnPower = 0.3;

        SmartDashboard.putNumber("turn.targetAngle", turnAngle);
        SmartDashboard.putBoolean("turn.left", left);

        // reset gyro sensor to zero
       mr_gyro.reset();   // do not calibrate as this will stop the world and make the gyro crazy

        long startTime = System.currentTimeMillis();
        long timeTaken = 0;

        while ( (Math.abs(getModGyroAngle()) < (Math.abs(turnAngle-10))) && (timeTaken < 5000) )
        {
            if (left)
            {
                setPower(turnPower, -turnPower);
            }
            else
            {
                // must want to turn right
                setPower(-turnPower, turnPower);
            }

            timeTaken = System.currentTimeMillis() - startTime;
        }

        hardStop();

        logger.info("mr. Gyro turn finished hopfully no issues :|");

    }*/
 

  
  public void arcadeDiffDrive(double stickX, double stickY){

    SmartDashboard.getNumber("Stick X: ", stickX);
    SmartDashboard.getNumber("Stick Y: ", stickY);
    robotDrive.arcadeDrive(stickY, stickX);
  }

  @Override
  public void publishStats() {
    SmartDashboard.putNumber("Drivetrain: gyro ",getGyroAngle());
    SmartDashboard.putNumber("Drivetrain Left Toughbox power",leftTB.get());
    SmartDashboard.putNumber("Drivetrain Right Toughbox power", rightTB.get());

  }

  @Override
  public void test() {

  }
}
