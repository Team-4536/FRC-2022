package frc4536.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToUpper extends CommandBase  {

    private final CargoArm m_cargoArmSubsystem;

    public CargoArmToUpper(CargoArm cargoArm) {
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

        if (currentShoulderPos < CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
            SmartDashboard.putString("Current Loop", "loop 1");
            m_cargoArmSubsystem.moveShoulder(0);
            m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          else {
            SmartDashboard.putString("Current Loop", "loop 2");
            m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
            m_cargoArmSubsystem.moveElbow(0);
          }
        } else if (currentShoulderPos < CargoArmInfo.CARGOARM_SHOULDER_UPPER_POSITION) {
          m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION){
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER * ((targetElbowPos - currentElbowPos)/3000));
          }
        }
        else {
          m_cargoArmSubsystem.moveShoulder(0.0);
          if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION) {
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          
          }
          else {
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


