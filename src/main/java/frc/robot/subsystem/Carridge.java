/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Created by Ollie 1/25/19
 */
public class Carridge implements SubSystem {

  VictorSP carridgeMotor = new VictorSP(8);
  private Pneumatics pneumatics;
  // boolean pressureSwitchEnabled = compressor.getPressureSwitchValue();
  // double compressorCurrent = compressor.getCompressorCurrent();

  public void shootOut(double power) {
    carridgeMotor.set(-power);
    // pneumatics
    pneumatics.cylinderOut();
  }
  // set to proper speeds defined by the carriage team!

  public void takeIn(double power) {
    // intake.intakeSuckIn(intakePower);
    carridgeMotor.set(power);
  }

  public void pullPneumaticsBack(){
    pneumatics.cyclinderIn();
  }

  @Override
  public void test() {

  }

  @Override
  public void publishStats() {

    // SmartDashboard.putBoolean("Is the pressure switch on the compressor on or
    // off?", pressureSwitchEnabled);
    // SmartDashboard.putNumber("Compressor Current", compressorCurrent);

  }
}
