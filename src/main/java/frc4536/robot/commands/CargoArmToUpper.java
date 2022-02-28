package frc4536.robot.commands;

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
        int currentShoulderPos = m_cargoArmSubsystem.getShoulderPosition();
        int currentElbowPos = m_cargoArmSubsystem.getElbowPosition();
        int restingShoulderPos = 0;
        int intermediateShoulderPos = 30;
        int upperShoulderPos = 70;
        int intakeElbowPos = 60;
        int restingElbowPos = 0;
        int intermediateElbowPos = 10;
        int finalElbowPos = 0;

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

        if (currentShoulderPos < intermediateShoulderPos) {
          if (currentElbowPos < intermediateElbowPos) {
            m_cargoArmSubsystem.moveShoulder(0);
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          else {
            m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
            m_cargoArmSubsystem.moveElbow(0);
          }
        } else if (currentShoulderPos < upperShoulderPos) {
          
          m_cargoArmSubsystem.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
          if (currentElbowPos < finalElbowPos) {
            m_cargoArmSubsystem.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
          }
          else {
            m_cargoArmSubsystem.moveElbow(0);
          }
          
        }
        else {
          m_cargoArmSubsystem.moveShoulder(0.0);
          if (currentElbowPos < finalElbowPos) {
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


