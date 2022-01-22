package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;

public class CargoMover extends SubsystemBase {
    private Spark m_cargoMoverElbow;
    private Spark m_cargoMoverShoulder;

    public CargoMover(){
        m_cargoMoverElbow = new Spark(Constants.CargoMoverInfo.CARGO_MOVER_ELBOW_ID);
        m_cargoMoverShoulder = new Spark(Constants.CargoMoverInfo.CARGO_MOVER_SHOULDER_ID);
    }

    
}
