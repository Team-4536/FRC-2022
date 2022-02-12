package frc4536.robot.commands.Autos;

import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;

public class ConstantsTest extends SequentialCommandGroup{
    public ConstantsTest(DriveTrain driveTrain, Pose2d initialPose, Trajectory constantsTest){
        addCommands(
            driveTrain.scurveTo(constantsTest)
        );
    }
    @Override
    public String getName() {
        return "Constants Test";
    }
}
