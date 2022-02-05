package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.math.controller.PIDController;

import frc4536.robot.Constants.DriveInfo;;

public class DriveTrain extends SubsystemBase{
    private final  DifferentialDrive m_differentialDrive;
    private final Encoder m_leftDriveEncoder;
    private final Encoder m_rightDriveEncoder;
    private final MotorControllerGroup m_leftMotorControllerGroup;
    private final MotorControllerGroup m_rightMotorControllerGroup;
    private TrajectoryConfig m_config;

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

        m_leftDriveEncoder = new Encoder(DriveInfo.LEFT_DRIVE_ENCODER_CHANNEL_A, 
                                         DriveInfo.LEFT_DRIVE_ENCODER_CHANNEL_B, 
                                         DriveInfo.LEFT_DRIVE_MOTORS_ARE_INVERTED, 
                                         DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE); 
        m_rightDriveEncoder = new Encoder(DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_A,
                                          DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_B, 
                                          DriveInfo.RIGHT_DRIVE_MOTORS_ARE_INVERTED, 
                                          DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE);  
                                          
        m_config = new TrajectoryConfig(0.5,05);
        //TODO: put in robot constants for max cceleration and max velocity instead of the 0.5s
    } 

    public void arcadeDrive(double driveSpeed, double robotRotation){
        m_differentialDrive.arcadeDrive(driveSpeed, robotRotation);
    }
    public void tankDrive(double leftSideSpeed, double rightSideSpeed ){
        m_differentialDrive.tankDrive(leftSideSpeed, rightSideSpeed);
    }

    public void resetEncoders() {
        m_leftDriveEncoder.reset();
        m_rightDriveEncoder.reset();
    }

    public double leftDriveMotorSpeed(){
        return m_leftMotorControllerGroup.get();
    }
    public double rightDriveMotorSpeed(){
        return m_rightMotorControllerGroup.get();
    }

    public int getLeftDriveEncoderCount(){
        return m_leftDriveEncoder.get();
    }
    public int getRightDriveEncoderCount(){
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Drive Speed", leftDriveMotorSpeed());
        SmartDashboard.putNumber("Right Drive Speed", rightDriveMotorSpeed());
        SmartDashboard.putNumber("Left Encoder Value", getLeftDriveEncoderCount());
        SmartDashboard.putNumber("Right Encoder Value", getRightDriveEncoderCount());
    }
}
