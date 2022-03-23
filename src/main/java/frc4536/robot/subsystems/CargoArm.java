package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Counter;

import frc4536.robot.Constants;
import frc4536.robot.Constants.CargoArmInfo;

public class CargoArm extends SubsystemBase{

    private DigitalInput m_elbowHome;
    private Counter m_wall;
    

    private Spark m_cargoArmElbow;
    private CANSparkMax m_cargoArmShoulder;
    private Encoder m_cargoArmElbowEncoder;
    private RelativeEncoder m_cargoArmShoulderEncoder;
    

    public CargoArm(){
        m_cargoArmElbow = new Spark(CargoArmInfo.CARGO_ARM_ELBOW_ID);
        m_cargoArmShoulder = new CANSparkMax(CargoArmInfo.CARGO_ARM_SHOULDER_ID, CargoArmInfo.CARGOARM_SHOULDER_BRUSHED_TYPE);
        m_cargoArmShoulder.setInverted(Constants.CargoArmInfo.CARGO_ARM_SHOULDER_ENCODER_IS_INVERTED);
        m_cargoArmElbow.setInverted(Constants.CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED);

        m_cargoArmElbow.setInverted(CargoArmInfo.CARGO_ARM_ELBOW_MOTOR_IS_INVERTED);
        m_cargoArmShoulder.setInverted(CargoArmInfo.CARGO_ARM_SHOULDER_MOTOR_IS_INVERTED);

        m_elbowHome = new DigitalInput(CargoArmInfo.CARGO_ARM_ELBOW_HOME_ID);
        m_wall = new Counter(0);
 
        m_cargoArmElbowEncoder = new Encoder(CargoArmInfo.CHANNEL_A_CARGO_ARM_ELBOW_ENCODER,
                CargoArmInfo.CHANNEL_B_CARGO_ARM_ELBOW_ENCODER, 
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED,
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_TYPE);
        m_cargoArmShoulderEncoder = m_cargoArmShoulder.getEncoder();
    }

    public void moveElbow(double power){
        m_cargoArmElbow.set(power);
    }

    public void moveShoulder(double power){
        m_cargoArmShoulder.set(power);
    }

    public double getElbowPosition(){
        return m_cargoArmElbowEncoder.get();
    }
    
    public double getShoulderPosition(){
        return m_cargoArmShoulderEncoder.getPosition();
    }

    public boolean elbowIsHome(){
        return !m_elbowHome.get();
    }
    public double wall(){
        return m_wall.get();
    }

    @Override
    public void periodic() {
        if (CargoArmInfo.CARGO_ARM_IN_DASHBOARD){
            SmartDashboard.putNumber("Elbow Positions", getElbowPosition());
            SmartDashboard.putNumber("Shoulder Positions", getShoulderPosition());
            SmartDashboard.putNumber("Elbow Motor", m_cargoArmElbow.get());
            SmartDashboard.putNumber("Shoulder Motor", m_cargoArmShoulder.get());
            SmartDashboard.putBoolean("is elbow at home position", elbowIsHome());
            SmartDashboard.putNumber("wall distance :)", wall());
        }
    }
}