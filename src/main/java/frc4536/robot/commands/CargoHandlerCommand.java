package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc4536.robot.subsystems.CargoHandler;


public class CargoHandlerCommand extends CommandBase{
    private final CargoHandler m_cargoHandler;
    
public CargoHandlerCommand(CargoHandler cargoHandler){
    m_cargoHandler = cargoHandler;

    addRequirements(m_cargoHandler);
}

public void intitalize(){}




}
    

