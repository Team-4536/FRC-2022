package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class ScurveTo {
        private m_gyro = new gyroscope;
    public scurveTo(Trajectory trajectory){
    System.out.println("Pathing to: " + trajectory.sample(trajectory.getTotalTimeSeconds()).poseMeters.toString()
                + " from " + trajectory.getInitialPose().toString());
        return new RamseteCommand(
                trajectory,
                this::getPose,
                new RamseteController(DriveInfo.RAMSETE_B_TUNING_PARAMETER, DriveInfo.RAMSETE_ZETA_TUNING_PARAMETER),
                new SimpleMotorFeedforward(DriveInfo.SCURVE_TO_KS,
                        DriveInfo.SCURVE_TO_KV,
                        DriveInfo.SCURVE_TO_KA),
                kDriveKinematics,
                this::getSpeeds,
                new PIDController(DriveInfo.KP_DRIVE_VELOCITY, 0, 0),
                new PIDController(DriveInfo.KP_DRIVE_VELOCITY, 0, 0),
                this::setOutput,
                this).andThen(new InstantCommand(() -> setOutput(0, 0)));
    }   
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(m_gyro.getAngle());
    
}