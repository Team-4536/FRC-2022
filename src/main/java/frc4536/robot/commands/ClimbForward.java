package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.Climber;

public class ClimbForward extends CommandBase{

    private Climber m_climber;

    public  ClimbForward(Climber climber) {
        m_climber = climber;
        addRequirements(climber);
      }

      @Override
      public void initialize() {
        //TODO reset gyroscope
      }
    
      @Override
      public void execute() {
       m_climber.climbForward();
      }

      @Override
      public void end(boolean interrupted) {
        m_climber.stopClimbing();
      }

      @Override
      public boolean isFinished() {
        return false;
  }
  





}
//great