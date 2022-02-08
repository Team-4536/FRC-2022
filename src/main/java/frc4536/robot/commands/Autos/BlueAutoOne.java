package frc4536.robot.commands.Autos;

import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
public class BlueAutoOne extends SequentialCommandGroup{
    //TODO: add intake and output subsystem
    public BlueAutoOne(DriveTrain driveTrain, Pose2d initialPose, Trajectory blueAutoOneTrajectoryOne ) {
    //TODO: add intake command and shoot command 
        addCommands(
            driveTrain.scurveTo(blueAutoOneTrajectoryOne)
        );
    }
    @Override
    public String getName() {
        return "Blue Auto One";
    }
}
