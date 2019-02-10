/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.intakes;

import frc.robot.subsystem.Pneumatics;
import frc.robot.subsystem.SubSystem;

/**
 * Created by Ollie 1/25/19
 */
public class Hatch_Intake implements SubSystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Pneumatics pneumatics;

  // DoubleSolenoid hatch = new DoubleSolenoid(0, 2);

  public void shootOut(){
    pneumatics.cylinderOut();
  }

  public void pullIn(){
    pneumatics.cyclinderIn();
  }

  public void test() {

  }

  public void publishStats() {

  }

}
