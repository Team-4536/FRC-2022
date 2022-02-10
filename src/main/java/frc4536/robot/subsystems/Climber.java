package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants.ClimberInfo;

public class Climber extends SubsystemBase {

    public CANSparkMax m_climbMotor;

    public Climber) {
        m_climbMotor = new CANSparkMax(ClimberInfo.CLIMBER_MOTOR_ID, ClimberInfo.CLIMBER_MOTOR_BRUSHED_TYPE);
        m_climbMotor.setInverted(ClimberInfo.CLIMBER_MOTOR_IS_INVERTED);
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climb Speed", m_climbMotor.get()); 
    }

}