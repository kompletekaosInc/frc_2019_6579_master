/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.intakes;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.subsystem.SubSystem;

/**
 * Created by Ollie 1/25/19
 */
public class Ball_intake implements SubSystem {

  //motor controllers
  private VictorSP pullUp_motor = new VictorSP(6);
  private Spark intake_Ball_Motor = new Spark(4);

  public void intakeUp(double power){
    pullUp_motor.set(-power);
  }

  public void intakeDown(double power){
    pullUp_motor.set(power);
  }

  public void intakeSuckIn(double power){
    intake_Ball_Motor.set(power);
  }

  public void intakeSuckOut(double power){
    intake_Ball_Motor.set(-power);
  }

  @Override
  public void publishStats(){

  }
  @Override
  public void test(){
    
  }

}
