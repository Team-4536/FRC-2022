package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.*;

import frc4536.robot.Constants.DriveInfo;
import frc4536.robot.Constants.RobotInfo;

public class DriveTrain extends SubsystemBase{
    private final  DifferentialDrive m_differentialDrive;
    private final Encoder m_leftDriveEncoder;
    private final Encoder m_rightDriveEncoder;
    private final MotorControllerGroup m_leftMotorControllerGroup;
    private final MotorControllerGroup m_rightMotorControllerGroup;

    private TrajectoryConfig m_config;
    private DifferentialDriveKinematics kDriveKinematics;
    private Pose2d m_pose = new Pose2d();

    public DriveTrain(){
        CANSparkMax frontLeftDriveMotor = new CANSparkMax(DriveInfo.LEFT_FRONT_DRIVE_MOTOR_ID, DriveInfo.DRIVE_MOTOR_BRUSHED_TYPE);
        CANSparkMax frontRightDriveMotor = new CANSparkMax(DriveInfo.RIGHT_FRONT_DRIVE_MOTOR_ID, DriveInfo.DRIVE_MOTOR_BRUSHED_TYPE);
        CANSparkMax backLeftDriveMotor = new CANSparkMax(DriveInfo.LEFT_REAR_DRIVE_MOTOR_ID, DriveInfo.DRIVE_MOTOR_BRUSHED_TYPE);
        CANSparkMax backRightDriveMotor = new CANSparkMax(DriveInfo.RIGHT_REAR_DRIVE_MOTOR_ID, DriveInfo.DRIVE_MOTOR_BRUSHED_TYPE);

        m_leftMotorControllerGroup = new MotorControllerGroup(frontLeftDriveMotor, backLeftDriveMotor);
        m_rightMotorControllerGroup = new MotorControllerGroup(frontRightDriveMotor, backRightDriveMotor);

        m_leftMotorControllerGroup.setInverted(DriveInfo.LEFT_DRIVE_MOTORS_ARE_INVERTED);
        m_rightMotorControllerGroup.setInverted(DriveInfo.RIGHT_DRIVE_MOTORS_ARE_INVERTED);

        m_differentialDrive = new DifferentialDrive(m_leftMotorControllerGroup, m_rightMotorControllerGroup);
        m_differentialDrive.setDeadband(DriveInfo.DIFFERENTIAL_DRIVE_DEADBAND);
        m_differentialDrive.setMaxOutput(DriveInfo.SET_MAX_RATE);

        m_leftDriveEncoder = new Encoder(DriveInfo.LEFT_DRIVE_ENCODER_CHANNEL_A, 
                                         DriveInfo.LEFT_DRIVE_ENCODER_CHANNEL_B, 
                                         DriveInfo.LEFT_DRIVE_ENCODER_IS_INVERTED, 
                                         DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE); 
        m_rightDriveEncoder = new Encoder(DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_A,
                                          DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_B, 
                                          DriveInfo.RIGHT_DRIVE_MOTORS_ARE_INVERTED, 
                                          DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE);  
                                          
        m_config = new TrajectoryConfig(0.5, DriveInfo.KP_DRIVE_VELOCITY);
        //TODO: put in robot constants for max cceleration and max velocity instead of the 0.5s
        kDriveKinematics = new DifferentialDriveKinematics(22.2);                         
    } 

    public void arcadeDrive(double driveSpeed, double robotRotation){
        double safeDriveSpeed = 
            (m_rightDriveEncoder.getRate() + m_leftDriveEncoder.getRate()) * robotRotation > 6000 ?  0.0 : driveSpeed;
        m_differentialDrive.arcadeDrive(safeDriveSpeed, robotRotation);
    }

    public void tankDrive(double leftSideSpeed, double rightSideSpeed ){
        m_differentialDrive.tankDrive(leftSideSpeed, rightSideSpeed);
    }
    public void stopDriving(){
        m_differentialDrive.tankDrive(0,0);
    }

    public void resetEncoders() {
        m_leftDriveEncoder.reset();
        m_rightDriveEncoder.reset();
    }
    public Pose2d getPose() {
        return m_pose;
    }
    public void setOutput(double leftVolts, double rightVolts) {
        m_leftMotorControllerGroup.setVoltage(leftVolts);
        m_rightMotorControllerGroup.setVoltage(rightVolts);
        m_differentialDrive.feed(); // to prevent the MotorSafety from stopping the robot
    }

    public double leftDriveMotorSpeed(){
        return m_leftMotorControllerGroup.get();
    }
    public double rightDriveMotorSpeed(){
        return m_rightMotorControllerGroup.get();
    }

    public double getLeftDriveEncoderCount(){
        return m_leftDriveEncoder.get();
    }
    public double getRightDriveEncoderCount(){
        return m_rightDriveEncoder.get();
    }
    public boolean getRightDriveEncoderDirection(){
        return m_rightDriveEncoder.getDirection();
    }
    public boolean getLeftDriveEncoderDirection(){
        return m_leftDriveEncoder.getDirection();
    }
    
    public boolean isRightDriveEncoderStopped(){
        return m_rightDriveEncoder.getStopped();
    }
    public boolean isLeftDriveEncoderStopped(){
        return m_leftDriveEncoder.getStopped();
    }

    public TrajectoryConfig getConfig(){
        return m_config;
    }
    public DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                leftDriveMotorSpeed() * RobotInfo.WHEEL_CIRCUMFERENCE_IN_INCHES,
                rightDriveMotorSpeed() * RobotInfo.WHEEL_CIRCUMFERENCE_IN_INCHES
        );
    }

    //TODO:change numbers in scurveTo to the proper variables 
    public Command scurveTo(Trajectory trajectory) {
        System.out.println("Pathing to: " + trajectory.sample(trajectory.getTotalTimeSeconds()).poseMeters.toString() + " from " + trajectory.getInitialPose().toString());
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
                this
        ).andThen(new InstantCommand(() -> setOutput(0,0)));
    }

    @Override
    public void periodic() { 
        if (DriveInfo.SHOW_DRIVETRAIN_IN_DASHBOARD){
            SmartDashboard.putNumber("Left Drive Speed", leftDriveMotorSpeed());
            SmartDashboard.putNumber("Right Drive Speed", rightDriveMotorSpeed());
            SmartDashboard.putNumber("Left Drive Encoder Value", getLeftDriveEncoderCount());
            SmartDashboard.putNumber("Right Drive Encoder Value", getRightDriveEncoderCount());
        }
    }
}
