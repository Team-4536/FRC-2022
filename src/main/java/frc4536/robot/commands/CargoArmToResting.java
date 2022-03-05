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
    
        /*
            if(cs < 1st){
              ce = 0
              cs = 0.25
            } else if (cs < 2nd){
              if(ce < 1){
                cs = 0
                ce = 0.25
              }else {
                cs = 0.25
                ce= 0
              }
            } else if (cs < 3rd) {
              cs = 0.25
              ce = -cs/0.6

            } else{
              cs = 0
              ce = 0
            } 
        */

        //COPY OF TO UPPER
        if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
          if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
            SmartDashboard.putString("Current Loop", "loop 1");
            m_cargoArmSubsystem.moveShoulder(0);
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          else {
            SmartDashboard.putString("Current Loop", "loop 2");
            m_cargoArmSubsystem.moveShoulder(-CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
            m_cargoArmSubsystem.moveElbow(0);
          }
        } else if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_UPPER_POSITION) {
          m_cargoArmSubsystem.moveShoulder(-CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION) {
            m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
            SmartDashboard.putString("Current Loop", "loop 3");
          }
          else {
            m_cargoArmSubsystem.moveElbow(0);
            SmartDashboard.putString("Current Loop", "loop 4");
          }
          
        }
        else {
          m_cargoArmSubsystem.moveShoulder(0.0);
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION) {
            m_cargoArmSubsystem.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          
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


