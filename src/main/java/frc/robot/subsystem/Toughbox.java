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
public class Toughbox {

  private VictorSP motorA;
  private VictorSP motorB;

  /**
   * This establishes the toughbox, and requires the ports of both motors in the
   * toughbox
   * 
   * @param pwmA
   * @param pwmB
   */
  public Toughbox(int pwmA, int pwmB) {
    motorA = new VictorSP(pwmA);
    motorB = new VictorSP(pwmB);
  }

  /**
   * This sets the toughbox motors to the same power
   * 
   * @param power
   */
  public void set(double power) {
    motorA.set(power);
    motorB.set(power);
  }

  /**
   * This returns the current power of the motor=s in the toughbox motorA is
   * always going to equal motorB, so only calling one is fine
   * 
   * @return
   */
  public double get() {
    return motorA.get();
  }
}
