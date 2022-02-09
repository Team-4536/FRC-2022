package frc4536.robot.commands.Autos;

import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;

public class BlueAutoThree extends SequentialCommandGroup{
    public BlueAutoThree(DriveTrain driveTrain, Pose2d initialPose, Trajectory blueAutoThreeTrajectoryOne, Trajectory blueAutoThreeTrajectoryTwo){
        addCommands(
            //start intake command 
            driveTrain.scurveTo(blueAutoThreeTrajectoryOne),
            //stop intake command
            //run shoot comand 
            //start intake command 
            driveTrain.scurveTo(blueAutoThreeTrajectoryTwo)
            //stop intake command 
            //run shoot command 
        );
    }
    @Override
    public String getName() {
        return "Blue Auto Two";
    }
}
