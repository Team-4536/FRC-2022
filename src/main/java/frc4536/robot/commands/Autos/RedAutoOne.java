package frc4536.robot.commands.Autos;

import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;

public class RedAutoOne extends SequentialCommandGroup {
    public RedAutoOne(DriveTrain driveTrain, Pose2d initialPose, Trajectory redAutoOneTrajectoryOne){
            addCommands(
                //initial pose is score three 
                //shoot command 
                //start intake command 
                driveTrain.scurveTo(redAutoOneTrajectoryOne)
                //stop intake command 
                //shoot command 
            );
    }
    @Override
    public String getName() {
        return "Red Auto One";
    }
}
