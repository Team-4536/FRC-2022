package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants.ClimberInfo;

public class Climber extends SubsystemBase {

    public CANSparkMax m_climbMotor;

    public Climber() {
        CANSparkMax climberMotor = new CANSparkMax(ClimberInfo.CLIMBER_MOTOR_ID, ClimberInfo.CLIMBER_MOTOR_BRUSHED_TYPE);
        m_climbMotor = climberMotor;
    }

    public void climbForward(){
        m_climbMotor.set(0.5);
    } 

    public void climbBackward() {
        m_climbMotor.set(-0.5);
    }

    public void stopClimbing() {
        m_climbMotor.set(0.0);
    }

}