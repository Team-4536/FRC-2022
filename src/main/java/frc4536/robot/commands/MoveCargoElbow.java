package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.CargoArm;
import frc4536.robot.subsystems.ExampleSubsystem;

public class MoveCargoElbow extends CommandBase  {
    private final int m_setPoint;
    private final CargoArm m_cargoArmSubsystem;

    public MoveCargoElbow(int setPoint, CargoArm subsystem) {
        m_cargoArmSubsystem = subsystem;
        m_setPoint = setPoint;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
      }
    @Override
    public void initialize() {}

    @Override
    public void execute() {
     
     
    }





}