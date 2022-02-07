package frc4536.robot.commands.Autos;
import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
//this auto will run through every pose in poses.java to ensure they are in the correct spot 
public class PoseCheckAuto extends SequentialCommandGroup{

    public PoseCheckAuto(DriveTrain driveTrain, Pose2d initialPose, Trajectory poseCheck ) {
    
        addCommands(
            driveTrain.scurveTo(poseCheck)
        );
    }
    @Override
    public String getName() {
        return "Pose Check Auto";
    }
}
