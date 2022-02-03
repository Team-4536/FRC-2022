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
    /*
    Two setpoints will be used. cargo elbow will only be able to move if cargo shoulder is
    in resting/downward/cargo pickup position. When moving fron bottom to top setpoints, both 
    cargo motors will rotate inversely porportional to each other. When cargo elbow is moving 
    from top to bottom setpoint, it must gradually retract into resting position unless it's 
    moving into active/pickup position. During this process and each time the shoulder motor 
    moves between setpoints, the angle of the cargo handler must be perpendicular to the ground.
    Total extension of cargo handler must not exceed 16 inches beyond the inside of the robot's
     bumper at *any* time.
    */
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
    @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.  
  @Override
  public boolean isFinished() {
    return false;
  }
  
}




