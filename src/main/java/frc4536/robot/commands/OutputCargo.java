package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.CargoHandler;

public class OutputCargo extends CommandBase {
    public CargoHandler m_cargoHandler;

    public OutputCargo(CargoHandler cargoHandler) {
        m_cargoHandler = cargoHandler;
        addRequirements(cargoHandler);
    }
    
    @Override
    public void execute() {
        m_cargoHandler.outputCargo();
    }

    @Override
    public void end(boolean interrupted) {
        m_cargoHandler.stopRotating();
    }
}