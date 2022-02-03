package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants;
import frc4536.robot.subsystems.CargoArm;
import frc4536.robot.subsystems.ExampleSubsystem;

public class MoveCargoElbow extends CommandBase  {
    private final int m_elbowSetPoint;
    private final CargoArm m_cargoArmSubsystem;

    public MoveCargoElbow(int elbowSetPoint, CargoArm cargoArm) {
        m_cargoArmSubsystem = cargoArm;
        m_elbowSetPoint = elbowSetPoint;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(cargoArm);
      }
    @Override
    public void initialize() {}

    @Override
    public void execute() {
      if (m_cargoArmSubsystem.getElbowPosition() < m_elbowSetPoint) {
        m_cargoArmSubsystem.moveElbow(-Constants.CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
      }
      else if (m_cargoArmSubsystem.getElbowPosition() > m_elbowSetPoint) {
        m_cargoArmSubsystem.moveElbow(Constants.CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
      }
      else {m_cargoArmSubsystem.moveElbow(0);
     }
    }
    }




