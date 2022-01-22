package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;


public class CargoMover extends SubsystemBase {
    private Spark m_cargoMoverElbow;
    private Spark m_cargoMoverShoulder;
    private Encoder m_cargoMoverElbowEncoder;
    private Encoder m_cargoMoverShoulderEncoder;

    public CargoMover(){
        m_cargoMoverElbow = new Spark(Constants.CargoMoverInfo.CARGO_MOVER_ELBOW_ID);
        m_cargoMoverShoulder = new Spark(Constants.CargoMoverInfo.CARGO_MOVER_SHOULDER_ID);

        m_cargoMoverElbowEncoder = new Encoder( Constants.CargoMoverInfo.CHANNEL_A_CARGO_MOVER_ELBOW_ENCODER,
            Constants.CargoMoverInfo.CHANNEL_B_CARGO_MOVER_ELBOW_ENCODER, Constants.CargoMoverInfo.CARGO_MOVER_ELBOW_ENCODER_INVERT, CounterBase.EncodingType.k2X);
        m_cargoMoverShoulderEncoder = new Encoder( Constants.CargoMoverInfo.CHANNEL_A_CARGO_MOVER_SHOULDER_ENCODER,
            Constants.CargoMoverInfo.CHANNEL_B_CARGO_MOVER_SHOULDER_ENCODER,
            Constants.CargoMoverInfo.CARGO_MOVER_SHOULDER_ENCODER_INVERT,CounterBase.EncodingType.k2X );

    }


    
}
