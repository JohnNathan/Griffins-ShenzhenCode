package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1884.util.TrapezoidProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TrapezoidSideTurnRight extends Command {

	private TrapezoidProfile traj;
	
    public TrapezoidSideTurnRight() {
    	requires(Robot.InstanceMap.driveTrain);
    	traj = new TrapezoidProfile(DriveTrain.tCfg, Math.PI/3.0 * 13.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	FollowTrapezoidProfile.instance.loadTrajectory(traj);
    	FollowTrapezoidProfile.instance.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	FollowTrapezoidProfile.instance.executeTurnRight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return FollowTrapezoidProfile.instance.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	FollowTrapezoidProfile.instance.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	FollowTrapezoidProfile.instance.interrupted();
    }
}
