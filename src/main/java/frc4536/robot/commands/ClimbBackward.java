package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.Climber;

public class ClimbBackward extends CommandBase{

    private Climber m_climber;

    public  ClimbBackward(Climber climber) {
        m_climber = climber;
        addRequirements(climber);
      }

      @Override
      public void initialize() {
        //m_gyroscope.reset();
        //m_gyroscope.resetDisplacement();
      }
    
      @Override
      public void execute() {
       m_climber.climbBackward();
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
