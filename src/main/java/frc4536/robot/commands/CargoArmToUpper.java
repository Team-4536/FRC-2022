package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToUpper extends CommandBase  {
    private final int m_lowerElbowSetPoint;
    private final int m_lowerShoulderSetPoint;
    private final CargoArm m_cargoArmSubsystem;

    public CargoArmToUpper(CargoArm cargoArm) {
      
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
      consider completely rewriting the execute object below. two setpoints must be used,
      (lower, upper)? methods should be defined to fluidly move between the upper and lower
      setpoints; one should be included to move in and out of the resting position as well.
    */
    public void execute() {
        int currentShoulderPosition = m_cargoArmSubsystem.getShoulderPosition();
        int currentElbowPosition = m_cargoArmSubsystem.getElbowPosition();
        int firstShoulderPos = 10;
        int finalShoulderPos = 20;
        int firstElbowPos = 10;
        int finalElbowPos = 0;
        //Disregard previous commit
     if (currentShoulderPosition < firstShoulderPos) {
       m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
      m_cargoArmSubsystem.moveElbow(0);
     } else {
      if (currentElbowPosition <= firstElbowPos) {
        m_cargoArmSubsystem.moveShoulder(0);
        m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
      }
      else if (currentShoulderPosition < finalShoulderPos && currentElbowPosition > finalElbowPos) {
          m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
      }
      else {
        m_cargoArmSubsystem.moveShoulder(0);
        m_cargoArmSubsystem.moveElbow(0);
      }
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




