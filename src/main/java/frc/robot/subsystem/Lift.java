/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Ollie 1/25/19
 */
public class Lift implements SubSystem {

  private static double HOLD_POWER = 0.1;

  private VictorSP liftMotor = new VictorSP(4);
  private VictorSP liftMotor2 = new VictorSP(5);
  private SpeedControllerGroup liftToughBox = new SpeedControllerGroup(liftMotor, liftMotor2);

  Encoder liftEncoder = null;

  public Lift(){
    try{
      liftEncoder = new Encoder(2,3,false,EncodingType.k4X);
      SmartDashboard.putBoolean("Lift encoder happy and working", true);
    } catch (Exception e){
      System.out.println("Lift encoder failed, something went wrong, please read following for more details, or call for help (they wont respond :)" + e.toString());
      SmartDashboard.putBoolean("Lift encoder happy and working", false);
    }
  }
   /**
     * Lifts the mechanism
     * @param power
     */
  public void liftUp(double power){
    liftToughBox.set(power);
  }

  public void liftDown(double power){
    liftToughBox.set(-power);
  }

  public void stop(){
    System.out.println("STOP, STOP, STOP THE LIFT NOW!!!!");
    liftToughBox.set(0);
  }

  public void hold(){
    System.out.println("HOLD THE LIFT OR IT WILL DIEEE");
    liftToughBox.set(HOLD_POWER);
  }

  public void resetEncoder(){
    liftEncoder.reset();
  }

 /* public void raiseToHeight(double targetDistance){
  
    moving = true;

    long startTime = System.currentTimeMillis();
    long timeTaken = 0;

 /*   while((getDistance() < targetDistance) && (timeTaken < 5000)){
      liftMotor.set(1);
      timeTaken = System.currentTimeMillis() - startTime;
  }*/
  /*    stop();
    hold();
    moving = false;
  }
*/
  public double getDistance(){
    return (liftEncoder.getRaw()*0.0734484025);
  }

  @Override
  public void publishStats() {
  //  SmartDashboard.putNumber("Lift encoder pulses", liftEncoder.getRaw());
    SmartDashboard.putNumber("Lift encoder distance", liftEncoder.getDistance()); //comparing the encoder's getDistance to our own calculation
  //  SmartDashboard.putNumber("Lift distance", getDistance()); //this is the accurate calculation
    SmartDashboard.putNumber("Lift Power", liftToughBox.get());
  }


  @Override
  public void test() {
    
  }
}
  