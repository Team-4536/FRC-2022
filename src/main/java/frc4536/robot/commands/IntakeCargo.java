package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.CargoHandler;

public class IntakeCargo extends CommandBase{
    public CargoHandler m_cargoHandler;

    public IntakeCargo(CargoHandler cargoHandler){
        m_cargoHandler = cargoHandler;
        addRequirements(m_cargoHandler);
    }

    @Override
    public void execute(){
        m_cargoHandler.intakeCargo();
    } 

    @Override
    public void end(boolean interrupted){
        m_cargoHandler.stopRotating(); 
    }
}
