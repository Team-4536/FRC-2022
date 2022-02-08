package frc4536.robot.commands.Autos;

import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;

public class RedAutoTwo extends SequentialCommandGroup{
    public RedAutoTwo(DriveTrain driveTrain, Pose2d initialPose, Trajectory redAutoOneTrajectoryTwo){
        addCommands(
            driveTrain.scurveTo(redAutoOneTrajectoryTwo)
        );
    }
    @Override
    public String getName() {
        return "Red Auto two";
    }
}
