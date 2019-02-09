/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Ollie 1/25/19
 */
public class Carridge implements SubSystem {

  Spark shootOut = new Spark(5);
//  boolean pressureSwitchEnabled = compressor.getPressureSwitchValue();
//  double compressorCurrent = compressor.getCompressorCurrent();


  public void shootOut(double power){
    shootOut.set(-power);
    //pneumatics
  }
  //set to proper speeds defined by the carriage team!

  public void takeIn(double power){
   // intake.intakeSuckIn(intakePower);
    shootOut.set(power);
  }
  @Override
  public void test(){

  }

  @Override
  public void publishStats(){

 //   SmartDashboard.putBoolean("Is the pressure switch on the compressor on or off?", pressureSwitchEnabled);
 //   SmartDashboard.putNumber("Compressor Current", compressorCurrent);

  }
}
