/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

/**
 * Created by Ollie 1/25/19
 */
public interface SubSystem {

    /**
     * Any SubSystem must be able to publish statistics about itself when requested.
     * These statistics should be published to the SmartDashboard.
     */
    public void publishStats();

    /**
     * This will only get called during test periodic which is only done in the
     * driver station. This allows us to specifically test a subsystem without
     * affecting our teleop code.
     */
    public void test();
}
