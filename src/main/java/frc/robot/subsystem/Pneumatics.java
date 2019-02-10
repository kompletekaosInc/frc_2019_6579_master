/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * Add your docs here.
 */
public class Pneumatics implements SubSystem {

    DoubleSolenoid solenoid;

    public Pneumatics() {

        try {
            solenoid = new DoubleSolenoid(1, 2);
            Shuffleboard.selectTab("Debugging");
        } catch (Exception e) {
            e.printStackTrace();
        }

        solenoid.set(DoubleSolenoid.Value.kOff);
    }

    public void initDoubleCommands() {

    }

    public void cylinderOut() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void cyclinderIn() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        solenoid.close();
    }

    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
