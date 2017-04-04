package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.commands.GearManDefault;
import org.usfirst.frc.team1884.util.DoubleSolenoidWrapper;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearManipulator extends Subsystem implements Debuggable {

	private DoubleSolenoidWrapper claw, linkage;
	private final static long claw_timer = 250L, linkage_timer = 800L;
	public final static DoubleSolenoid.Value 
			k_linkage_up = DoubleSolenoid.Value.kForward,
			k_linkage_down = DoubleSolenoid.Value.kReverse,
			k_claw_closed = DoubleSolenoid.Value.kReverse,
			k_claw_open = DoubleSolenoid.Value.kForward,
			k_off = DoubleSolenoid.Value.kOff;
	
	private DigitalInput gearSwitch;
	
    public GearManipulator() {
    	claw = new DoubleSolenoidWrapper(Robot.Map.GM_CLAW_F, Robot.Map.GM_CLAW_R, claw_timer);
    	linkage = new DoubleSolenoidWrapper(Robot.Map.GM_LINK_F, Robot.Map.GM_LINK_R, linkage_timer);
    	
    	this.closeClaw();
    	this.lowerClaw();
    	
    	gearSwitch = new DigitalInput(Robot.Map.GM_SWITCH);
    }
    
    public void liftClaw() {
    	if (Robot.InstanceMap.intake.isAtTop() && claw.getActualState() == k_claw_closed) {
    		linkage.set(k_linkage_up);
    	}
    }
    
    public void lowerClaw() {
    	if (claw.getActualState() == k_claw_closed) {
    		linkage.set(k_linkage_down);
    	}
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
    public boolean isGearIn() {
    	return gearSwitch.get();
    }
    public boolean isUp() {
    	return linkage.getActualState() == k_linkage_up;
    }
    public boolean finishedLinkage() {
    	return linkage.finishedExtension();
    }
    public boolean finishedClaw() {
    	return claw.finishedExtension();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new GearManDefault());
    }

	@Override
	public void updateSmartDashboard() {
		SmartDashboard.putString("GM Claw State", claw.getActualState() == k_claw_closed ? "CLOSED" : "OPEN");
	}
}

