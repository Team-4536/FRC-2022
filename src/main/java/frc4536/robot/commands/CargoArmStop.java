package frc4536.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmStop extends CommandBase{

    private final CargoArm m_cargoArm;

    public CargoArmStop(CargoArm cargoArm){
        m_cargoArm = cargoArm;
        addRequirements(m_cargoArm);
    }

    @Override
    public void execute() {
        m_cargoArm.moveElbow(0.0);
        m_cargoArm.moveShoulder(0.0);
    }
    
}
