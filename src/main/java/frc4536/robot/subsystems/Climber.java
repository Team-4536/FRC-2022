package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants.ClimberInfo;

public class Climber extends SubsystemBase {

    private final CANSparkMax m_climbMotor;
    private final RelativeEncoder m_climberEncoder;
    private double m_lastKnownPosition;

    public Climber() {
        m_climbMotor = new CANSparkMax(ClimberInfo.CLIMBER_MOTOR_ID, ClimberInfo.CLIMBER_MOTOR_BRUSHED_TYPE);
        m_climbMotor.setInverted(ClimberInfo.CLIMBER_MOTOR_IS_INVERTED);
        m_climberEncoder = m_climbMotor.getEncoder();
        m_lastKnownPosition = 0;
    }

    public void climbForward(){
        m_climbMotor.set(ClimberInfo.CLIMBER_MOTOR_SPEED);
    } 

    public void climbBackward() {
        m_climbMotor.set(-ClimberInfo.CLIMBER_MOTOR_SPEED);
    }

    public void stopClimbing() {
        m_climbMotor.set(0.0);
    }

    public void updateLastKnownPosition(){
        m_lastKnownPosition = getClimbEncoder();
    }

    public double getClimbEncoder() {
        return m_climberEncoder.getPosition() + m_lastKnownPosition;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climb Speed", m_climbMotor.get()); 
    }

}