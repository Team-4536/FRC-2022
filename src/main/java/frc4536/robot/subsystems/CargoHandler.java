package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;

public class CargoHandler extends SubsystemBase {
 private Spark m_cargoHandlerMotor;
 public CargoHandler(){
     m_cargoHandlerMotor = new Spark(Constants.CargoHandlerInfo.CARGO_HANDLER_MOTOR_ID);




 }

}
