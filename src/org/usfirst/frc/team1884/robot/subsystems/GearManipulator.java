package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.util.DoubleSolenoidWrapper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulator extends Subsystem {

	private DoubleSolenoidWrapper claw, linkage;
	private final static long claw_timer = 250L, linkage_timer = 800L;
	public final static DoubleSolenoid.Value 
			k_linkage_up = DoubleSolenoid.Value.kForward,
			k_linkage_down = DoubleSolenoid.Value.kReverse,
			k_claw_closed = DoubleSolenoid.Value.kReverse,
			k_claw_open = DoubleSolenoid.Value.kForward,
			k_off = DoubleSolenoid.Value.kOff;
	
    public GearManipulator() {
    	claw = new DoubleSolenoidWrapper(Robot.Map.GM_CLAW_F, Robot.Map.GM_CLAW_R, claw_timer);
    	linkage = new DoubleSolenoidWrapper(Robot.Map.GM_LINK_F, Robot.Map.GM_LINK_R, linkage_timer);
    }
    
    public void liftClaw() {
    	linkage.set(k_linkage_up);
    }
    
    public void lowerClaw() {
    	linkage.set(k_linkage_down);
    }
    
    public void openClaw() {
    	claw.set(k_claw_open);
    }
    
    public void closeClaw() {
    	claw.set(k_claw_closed);
    }
    
    public void update_pistons() {
    	linkage.update();
    	claw.update();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

