package frc4536.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;


public class CargoArmHoldInPlace extends CommandBase{

    private CargoArm m_cargoArmSubsystem; 
    
    private double m_cargoElbowTargetLocation;
    private double m_cargoShoulderTargetLocation;

    public CargoArmHoldInPlace(CargoArm cargoArm) {
        m_cargoArmSubsystem = cargoArm;
          // Use addRequirements() here to declare subsystem dependencies.
          addRequirements(cargoArm);
        }

@Override
public void initialize() {
    m_cargoElbowTargetLocation = m_cargoArmSubsystem.getElbowPosition();
    m_cargoShoulderTargetLocation = m_cargoArmSubsystem.getShoulderPosition();

  
}
 
@Override
public void execute() {

    double elbowError = (m_cargoElbowTargetLocation - m_cargoArmSubsystem.getElbowPosition())/10000;
    double shoulderError =  m_cargoShoulderTargetLocation - m_cargoArmSubsystem.getShoulderPosition();
    
    m_cargoArmSubsystem.moveElbow(elbowError * 0.5);
    m_cargoArmSubsystem.moveShoulder(shoulderError * 0.5);

    
}
@Override
public void end(boolean interrupted) {
    m_cargoArmSubsystem.moveElbow(0.0);
    m_cargoArmSubsystem.moveShoulder(0.0);
}
}
