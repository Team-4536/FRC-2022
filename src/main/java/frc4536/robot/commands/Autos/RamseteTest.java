package frc4536.robot.commands.Autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc4536.robot.Constants.DriveInfo;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

public class RamseteTest extends SequentialCommandGroup {

    private final DriveTrain m_driveTrain;
    private final Gyroscope m_gyroscope;

    public RamseteTest(DriveTrain driveTrain, Gyroscope gyroscope) {
        m_driveTrain = driveTrain;
        m_gyroscope = gyroscope;

        var kDriveKinematics = new DifferentialDriveKinematics(22.2);
        var trajectory = getTrajectory();

        new RamseteCommand(
                trajectory,
                m_driveTrain::getPose,
                new RamseteController(DriveInfo.RAMSETE_B_TUNING_PARAMETER, DriveInfo.RAMSETE_ZETA_TUNING_PARAMETER),
                new SimpleMotorFeedforward(DriveInfo.SCURVE_TO_KS,
                        DriveInfo.SCURVE_TO_KV,
                        DriveInfo.SCURVE_TO_KA),
                kDriveKinematics,
                m_driveTrain::getSpeeds,
                new PIDController(DriveInfo.KP_DRIVE_VELOCITY, 0, 0),
                new PIDController(DriveInfo.KP_DRIVE_VELOCITY, 0, 0),
                m_driveTrain::setOutput,
                m_driveTrain)
            .andThen(() -> m_driveTrain.setOutput(0.0, 0.0), m_driveTrain);
    }

    private Trajectory getTrajectory() {
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(3, 0, new Rotation2d(0)),
                // Pass config
                m_driveTrain.getConfig());
        return trajectory;
    }
}
