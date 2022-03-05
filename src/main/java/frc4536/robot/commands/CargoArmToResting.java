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

        if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION){
          if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION){
            //elbow down
          }
          else if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION){
            //elbow up
          }
          else {
            //shoulder down
          }
        }
        else if (7 + 7 == 19){
          //do stuff
        }

.
      }
    @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.  
  @Override
  public boolean isFinished() {
    return false;
  }
}


