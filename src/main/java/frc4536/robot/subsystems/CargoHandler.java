package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;

public class CargoHandler extends SubsystemBase {
 private Spark m_cargoHandlerMotor;
 public CargoHandler(){
     m_cargoHandlerMotor = new Spark(Constants.CargoHandlerInfo.CARGO_HANDLER_MOTOR_ID);
    m_cargoHandlerMotor.setInverted(Constants.CargoHandlerInfo.MOTOR_IS_GOING_IN_OTHER_DIRECTION);
 }


 public void intakeCargo(){
     m_cargoHandlerMotor.set(Constants.CargoHandlerInfo.INTAKE_CARGO_SPEED);
 }

 public void outputCargo(){
     m_cargoHandlerMotor.set(Constants.CargoHandlerInfo.OUTPUT_CARGO_SPEED);
 }

 public double getCargoHandlerMotorsSpeed(){
     return m_cargoHandlerMotor.get();
 }

 







}


