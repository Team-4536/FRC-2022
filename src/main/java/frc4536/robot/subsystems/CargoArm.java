package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;
import frc4536.robot.Constants.CargoArmInfo;
public class CargoArm extends SubsystemBase{
    private Spark m_cargoArmElbow;
    private CANSparkMax m_cargoArmShoulder;
    private Encoder m_cargoArmElbowEncoder;
    private RelativeEncoder m_cargoArmShoulderEncoder;

    public CargoArm(){
        m_cargoArmElbow = new Spark(CargoArmInfo.CARGO_ARM_ELBOW_ID);
        m_cargoArmShoulder = new CANSparkMax(CargoArmInfo.CARGO_ARM_SHOULDER_ID, CargoArmInfo.CARGOARM_SHOULDER_BRUSHED_TYPE);
        m_cargoArmShoulder.setInverted(Constants.CargoArmInfo.CARGO_ARM_SHOULDER_ENCODER_IS_INVERTED);
        m_cargoArmElbow.setInverted(Constants.CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED);

        m_cargoArmElbowEncoder = new Encoder(CargoArmInfo.CHANNEL_A_CARGO_ARM_ELBOW_ENCODER,
                CargoArmInfo.CHANNEL_B_CARGO_ARM_ELBOW_ENCODER, CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED,
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_TYPE);
        m_cargoArmShoulderEncoder = m_cargoArmShoulder.getEncoder();
    }

    public void moveElbow(double power){
        m_cargoArmElbow.set(power);
    }
    public void moveShoulder(double power){
        m_cargoArmShoulder.set(power);
    }

    public int getElbowPosition(){
        return m_cargoArmElbowEncoder.get();
    }
    public double getShoulderPosition(){
        return m_cargoArmShoulderEncoder.getPosition();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elbow Position", getElbowPosition());
        SmartDashboard.putNumber("Shoulder Position", getShoulderPosition());
        SmartDashboard.putNumber("Elbow Power", m_cargoArmElbow.get());
        SmartDashboard.putNumber("Shoulder Power", m_cargoArmShoulder.get());
    }
}