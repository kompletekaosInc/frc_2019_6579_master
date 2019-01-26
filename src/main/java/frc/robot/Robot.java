/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;

  private VictorSP intake;
  private VictorSP lift;
  private VictorSP up_down;

  @Override
  public void robotInit() {
    m_myRobot = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
    m_leftStick = new Joystick(0);
    intake = new VictorSP(7);
    lift = new VictorSP(4);
    up_down = new VictorSP(6);

    
    }
  

  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(m_leftStick.getY(), m_leftStick.getX());
    if(m_leftStick.getRawButton(11)){
      up_down.set(.3);
    } else if(m_leftStick.getRawButton(12)){
      up_down.set(-.3);
    } else if(m_leftStick.getRawButton(9)){
      up_down.set(.4);
    } else if(m_leftStick.getRawButton(10)){
      up_down.set(-.4);
    } else if(m_leftStick.getRawButton(7)){
      up_down.set(.5);
    } else if(m_leftStick.getRawButton(8)){
      up_down.set(-.5);
    } else {
      lift.set(0);
      intake.set(0);
      up_down.set(0);
    }
    
  }
}
