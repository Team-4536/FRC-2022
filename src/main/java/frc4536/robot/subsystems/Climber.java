package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc4536.robot.Constants.ClimberInfo;

public class Climber extends SubsystemBase {

    public CANSparkMax m_climbMotor;
    public DigitalInput m_limitSwitchAlpha;
    public DigitalInput m_limitSwitchBeta;
    private final RelativeEncoder m_climbEncoder;

    public Climber() {
        m_climbMotor = new CANSparkMax(ClimberInfo.CLIMBER_MOTOR_ID, ClimberInfo.CLIMBER_MOTOR_BRUSHED_TYPE);
        m_climbMotor.setInverted(ClimberInfo.CLIMBER_MOTOR_IS_INVERTED);

        m_limitSwitchAlpha = new DigitalInput(ClimberInfo.LIMIT_SWITCH_ALPHA_MOTOR_ID);
        m_limitSwitchBeta = new DigitalInput(ClimberInfo.LIMIT_SWITCH_BETA_MOTOR_ID);

        m_climbEncoder = m_climbMotor.getEncoder();
    }
   
    public void climbForward(){
        this.climbForward(ClimberInfo.CLIMBER_MOTOR_SPEED);
    }
    public void climbForward(double climbSpeed){
        m_climbMotor.set(Math.abs(climbSpeed));
    }

    public void climbBackward() {
        this.climbBackward(ClimberInfo.CLIMBER_MOTOR_SPEED);
    }
    public void climbBackward(double climbSpeed){
        m_climbMotor.set(-Math.abs(climbSpeed));
    }
    public void stopClimbing() {
        m_climbMotor.set(0.0);
    }
    
    public boolean limitSwitchAlphaIsTripped(){
        return m_limitSwitchAlpha.get();
    }
    public boolean limitSwitchBetaIsTripped(){
        return m_limitSwitchBeta.get();
    }

    public double getEncoderDistance() {
        return m_climbEncoder.getPosition();
    }
    public double getEncoderVelocity() {
        return m_climbEncoder.getVelocity();
    }

    @Override
    public void periodic() {
        if (ClimberInfo.SHOW_CLIMBER_IN_DASHBOARD){
            SmartDashboard.putNumber("Climb Speed", m_climbMotor.get());
            SmartDashboard.putNumber("Climber Location", m_climbEncoder.getPosition()); 
            SmartDashboard.putBoolean("is limit alpha switch tripped", limitSwitchAlphaIsTripped());
            SmartDashboard.putBoolean("is limit beta switch tripped", limitSwitchBetaIsTripped());
            SmartDashboard.putNumber("climber velocity", getEncoderVelocity());
        }
    }    
}