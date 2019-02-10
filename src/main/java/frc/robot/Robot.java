/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.control.DriverControl;
import frc.robot.control.OperatorControl;
import frc.robot.control.RobotControl;
import frc.robot.subsystem.Carridge;
import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.Lift;
import frc.robot.subsystem.Pneumatics;
import frc.robot.subsystem.intakes.Ball_intake;
import frc.robot.subsystem.intakes.Hatch_Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * Created 1/19/19 By Ollie
 */
public class Robot extends TimedRobot {

  // create subsystem variables
  private Hatch_Intake hatch = null;
  private Ball_intake ball = null;
  private Lift lift = null;
  private Carridge carridge = null;
  private Drivetrain drivetrain = null;
  private Pneumatics pneumatics = null;
  private double number = 0;

  // Add ShuffleBoard Tabs
  ShuffleboardTab debug = Shuffleboard.getTab("Debugging");

  // Intiliase contorl variables
  private RobotControl robotControl;
  private RobotControl operatorControl;

  private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    DateFormat dateformat = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss");
    Date date = new Date();
    System.out.println("robot Init: [Build=Ollie,compDay1]" + dateformat.format(date));

    try {
      hatch = new Hatch_Intake();
      ball = new Ball_intake();
      lift = new Lift();
      carridge = new Carridge();
      drivetrain = new Drivetrain();
      pneumatics = new Pneumatics();

      Shuffleboard.getTab("Debugging").add("Are the subsystems ready?", true);
    } catch (Exception e) {
      System.out.println("Error loading subsystems, Robot said sorry :(");
      e.getStackTrace();
      Shuffleboard.getTab("Debugging").add(("Are the subsystems ready?"), false);
    }

    // assume this is port 0
    try {
      robotControl = new DriverControl();
      Shuffleboard.getTab("Debugging").add("Are the driver controls happy", true);
      Shuffleboard.getTab("Debugging").add("Stick X", number);
    } catch (Exception e) {
      System.out.println("The joystick doesn't like this computer :|");
      Shuffleboard.getTab("Debugging").add("Are the driver controls happy", false);
      e.getStackTrace();
    }
    // assume this is port 1
    try {
      operatorControl = new OperatorControl();
      Shuffleboard.getTab("Debugging").add("Are the operator controls happy", true);
    } catch (Exception e) {
      System.out.println("The joystick doesn't like this computer :|");
      Shuffleboard.getTab("Debugging").add("Are the operator controls happy", false);
    }
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  // Insert Dank meme here... /kill @a /tp harri nathanial /give Lucas dirt block
  // 1 5 {CustomName: Bad Thing}
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    resetSensors();
    // DateFormat dateformat = new SimpleDateFormat("YYYY/MM/DD HH:MM:SS");
    // Date date = new Date();
    System.out.println("teleopInit: [build=Ollie]");
    resetSensors();
  }

  // resets all sensors on the robot
  private void resetSensors() {
    try {
      drivetrain.resetGyro();

      // lift.resetEncoder();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    if (robotControl != null) {
      robotControl.giveCommands(this);

    }

    if (operatorControl != null) {
      operatorControl.giveCommands(this);
    }

    publishSubSystemStats();
  }

  // simple accesor methods
  public Drivetrain getDrivetrain() {
    return drivetrain;
  };

  public Lift getLift() {
    return lift;
  };

  public Ball_intake getBall_intake() {
    return ball;
  };

  public Hatch_Intake getHatch_Intake() {
    return hatch;
  };

  public Carridge getCarridge() {
    return carridge;
  };

  public Pneumatics getPneumatics() {
    return pneumatics;
  };

  private void publishSubSystemStats() {
    lift.publishStats();
  }

  // m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
