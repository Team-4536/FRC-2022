package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc4536.robot.Constants.CargoArmInfo;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class CargoArm extends SubsystemBase {

    private DigitalInput m_elbowHome;
    private DigitalInput m_shoulderHome;

    private AnalogInput m_infraredSensor;

    private Spark m_cargoArmElbow;
    private CANSparkMax m_cargoArmShoulder;
    private Encoder m_cargoArmElbowEncoder;
    private RelativeEncoder m_cargoArmShoulderEncoder;

    public CargoArm() {
        m_cargoArmElbow = new Spark(CargoArmInfo.CARGO_ARM_ELBOW_ID);
        m_cargoArmShoulder = new CANSparkMax(CargoArmInfo.CARGO_ARM_SHOULDER_ID,
                CargoArmInfo.CARGOARM_SHOULDER_BRUSHED_TYPE);
        m_cargoArmShoulder.setInverted(CargoArmInfo.CARGO_ARM_SHOULDER_ENCODER_IS_INVERTED);
        m_cargoArmElbow.setInverted(CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED);

        m_cargoArmElbow.setInverted(CargoArmInfo.CARGO_ARM_ELBOW_MOTOR_IS_INVERTED);
        m_cargoArmShoulder.setInverted(CargoArmInfo.CARGO_ARM_SHOULDER_MOTOR_IS_INVERTED);

        m_elbowHome = new DigitalInput(CargoArmInfo.CARGO_ARM_ELBOW_HOME_ID);
        m_shoulderHome = new DigitalInput(CargoArmInfo.CARGO_ARM_SHOULDER_HOME_ID);

        m_infraredSensor = new AnalogInput(CargoArmInfo.CARGO_ARM_INFRARED_SENSOR_ID);

        m_cargoArmElbowEncoder = new Encoder(CargoArmInfo.CHANNEL_A_CARGO_ARM_ELBOW_ENCODER,
                CargoArmInfo.CHANNEL_B_CARGO_ARM_ELBOW_ENCODER,
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_IS_INVERTED,
                CargoArmInfo.CARGO_ARM_ELBOW_ENCODER_TYPE);
        m_cargoArmShoulderEncoder = m_cargoArmShoulder.getEncoder();
    }

    public void moveElbow(double power) {
        m_cargoArmElbow.set(power);
    }

    public void moveShoulder(double power) {
        m_cargoArmShoulder.set(power);
    }

    public double getElbowPosition() {
        return m_cargoArmElbowEncoder.get();
    }

    public double getShoulderPosition() {
        return m_cargoArmShoulderEncoder.getPosition();
    }

    public boolean elbowIsHome() {
        return !m_elbowHome.get();
    }

    public boolean shoulderIsHome() {
        return !m_shoulderHome.get();
    }

    public double infraredSensorGetValue() {
        return m_infraredSensor.getVoltage();
    }

    @Override
    public void periodic() {

        if (elbowIsHome()) {
            m_cargoArmElbowEncoder.reset();
        }

        if (shoulderIsHome()) {
            m_cargoArmShoulderEncoder.setPosition(0.0);
        }

        if (CargoArmInfo.CARGO_ARM_IN_DASHBOARD) {
            SmartDashboard.putNumber("Elbow Position", getElbowPosition());
            SmartDashboard.putNumber("Shoulder Position", getShoulderPosition());
            SmartDashboard.putNumber("Elbow Motor", m_cargoArmElbow.get());
            SmartDashboard.putNumber("Shoulder Motor", m_cargoArmShoulder.get());
            SmartDashboard.putBoolean("is elbow at home position", !m_elbowHome.get());
            SmartDashboard.putBoolean("is shoulder at home posotion", !m_shoulderHome.get());
            SmartDashboard.putNumber("Range Sensor", m_infraredSensor.getVoltage());
        }
    }
}