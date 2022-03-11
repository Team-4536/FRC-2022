package frc4536.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToResting extends CommandBase  {

    private final CargoArm m_cargoArmSubsystem;

    public CargoArmToResting(CargoArm cargoArm) {
      m_cargoArmSubsystem = cargoArm;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(cargoArm);
      }
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double currentShoulderPos = m_cargoArmSubsystem.getShoulderPosition();
        int currentElbowPos = m_cargoArmSubsystem.getElbowPosition();
        double targetElbowPos = (currentShoulderPos - CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION)*CargoArmInfo.CARGO_ARM_ELBOW_TO_SHOULDER_RATIO_ABOVE_INTERMEDIATE+CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION;

        if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION){
          m_cargoArmSubsystem.moveShoulder(-CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTAKE_ELBOW_POSITION){
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER * ((targetElbowPos - currentElbowPos)/(3000)));
          }
        }
        else if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_RESTING_POSITION){
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_RESTING_POSITION){
            m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_RESTING_POSITION){
            m_cargoArmSubsystem.moveShoulder(-CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          }
        }
        else {
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_RESTING_POSITION){
            m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          else{
            m_cargoArmSubsystem.moveElbow(0.0);
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

