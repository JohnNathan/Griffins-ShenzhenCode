
package org.usfirst.frc.team1884.robot;

import org.usfirst.frc.team1884.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1884.robot.subsystems.GearManipulator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static class Map {
		//Constants go here
		
		//PWM
		public final static int
			DT_LEFT_1 = 0,
			DT_LEFT_2 = 1,
			DT_RIGHT_1 = 2,
			DT_RIGHT_2 = 3;
		
		//PCM
		public final static int
			DT_LEFT_F = 0,
			DT_LEFT_R = 1,
			DT_RIGHT_F = 2,
			DT_RIGHT_R = 3,
			GM_CLAW_F = 4,
			GM_CLAW_R = 5,
			GM_LINK_F = 6,
			GM_LINK_R = 7;
		
		//DIO
		public final static int
			DT_L_ENC_A = 0,
			DT_L_ENC_B = 1,
			DT_R_ENC_A = 2,
			DT_R_ENC_B = 3;
		
		//PDP
		public final static int
			DT_LEFT_1_P = 0,
			DT_LEFT_2_P = 1,
			DT_RIGHT_1_P = 15,
			DT_RIGHT_2_P = 14;
		
	}
	
	static {
		InstanceMap.poke();
	}
	
	public static class InstanceMap {
		//Instances go here
		
		public final static DriveTrain driveTrain = new DriveTrain();
		public final static GearManipulator gearMan = new GearManipulator();
		
		private static void poke() { }
		
	}
	
	public static OI oi;

	@Override
	public void robotInit() {
		oi = new OI();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
