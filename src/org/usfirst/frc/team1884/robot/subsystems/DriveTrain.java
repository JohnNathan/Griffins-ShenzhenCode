package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.commands.DriveCommand;
import org.usfirst.frc.team1884.util.DoubleSolenoidWrapper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private VictorSP left1, left2, right1, right2;
	private DoubleSolenoidWrapper left_shifter, right_shifter;
	private Encoder left_enc, right_enc;
	
	private final static long shift_timer = 250L;
	
	private final static double k_distance_per_pulse = 0.0; //TODO
	private final static DoubleSolenoid.Value
			k_shifter_high_speed = DoubleSolenoid.Value.kForward,
			k_shifter_low_speed = DoubleSolenoid.Value.kReverse,
			k_off = DoubleSolenoid.Value.kOff;
	
	public DriveTrain() {
		
		left1 = new VictorSP(Robot.Map.DT_LEFT_1);
		left2 = new VictorSP(Robot.Map.DT_LEFT_2);
		right1 = new VictorSP(Robot.Map.DT_RIGHT_1);
		right2 = new VictorSP(Robot.Map.DT_RIGHT_2);
		
		left_shifter = new DoubleSolenoidWrapper(Robot.Map.DT_LEFT_F, Robot.Map.DT_LEFT_R, shift_timer);
		right_shifter = new DoubleSolenoidWrapper(Robot.Map.DT_RIGHT_F, Robot.Map.DT_RIGHT_R, shift_timer);
		
		shiftHigh();
		
		left_enc = new Encoder(Robot.Map.DT_L_ENC_A, Robot.Map.DT_L_ENC_B, false);
		right_enc = new Encoder(Robot.Map.DT_R_ENC_A, Robot.Map.DT_R_ENC_B, true);
		left_enc.setDistancePerPulse(k_distance_per_pulse);
		right_enc.setDistancePerPulse(k_distance_per_pulse);
	}
	
	public void setLeftSpeed(double speed) {
		left1.set(speed);
		left2.set(speed);
	}
	
	public void setRightSpeed(double speed) {
		right1.set(speed);
		right2.set(speed);
	}
	
	public void shiftHigh() {
		shifter_set(k_shifter_high_speed);
	}
	
	public void shiftLow() {
		shifter_set(k_shifter_low_speed);
	}
	
	public boolean isShiftDone() {
		return left_shifter.finishedExtension() && right_shifter.finishedExtension();
	}
	
	private void shifter_set(DoubleSolenoid.Value value) {
		left_shifter.set(value);
		right_shifter.set(value);
	}
	
	public void update_pistons() {
		left_shifter.update();
		right_shifter.update();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
}
