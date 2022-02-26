package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc4536.robot.Constants.CargoArmInfo;
public class CargoArm extends SubsystemBase{
    private Spark m_cargoArmElbow;
    private Spark m_cargoArmShoulder;
    private Encoder m_cargoArmElbowEncoder;
    private Encoder m_cargoArmShoulderEncoder;

    public CargoArm(){
        m_cargoArmElbow = new Spark(CargoArmInfo.CARGO_ARM_ELBOW_ID);
        m_cargoArmShoulder = new Spark(CargoArmInfo.CARGO_ARM_SHOULDER_ID);

        m_cargoArmElbowEncoder = new Encoder(CargoArmInfo.CHANNEL_A_CARGO_ARM_ELBOW_ENCODER,
                CargoArmInfo.CHANNEL_B_CARGO_ARM_ELBOW_ENCODER, CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED,
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_TYPE);
        m_cargoArmShoulderEncoder = new Encoder(CargoArmInfo.CHANNEL_A_CARGO_ARM_SHOULDER_ENCODER,
                CargoArmInfo.CHANNEL_B_CARGO_ARM_SHOULDER_ENCODER,
                CargoArmInfo.CARGO_ARM_SHOULDER_ENCODER_IS_INVERTED, CargoArmInfo.CARGO_ARM_SHOULDER_ENCODER_TYPE);
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
    public int getShoulderPosition(){
        return m_cargoArmShoulderEncoder.get();
    }
}