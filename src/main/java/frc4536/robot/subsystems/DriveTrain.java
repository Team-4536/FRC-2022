package frc4536.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc4536.robot.Constants;
import frc4536.robot.Constants.DriveInfo;

public class DriveTrain extends SubsystemBase{
    private final DifferentialDrive m_differentialDrive;
    private final MotorControllerGroup m_leftMotorControllerGroup;
    private final MotorControllerGroup m_rightMotorControllerGroup;
    private final Encoder m_leftDriveEncoder;
    private final Encoder m_rightDriveEncoder;

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
                                         DriveInfo.LEFT_DRIVE_ENCODER_IS_INVERTED, 
                                         DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE); 
        m_rightDriveEncoder = new Encoder(DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_A,
                                          DriveInfo.RIGHT_DRIVE_ENCODER_CHANNEL_B, 
                                          DriveInfo.RIGHT_DRIVE_ENCODER_IS_INVERTED, 
                                          DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE);                            
    } 

    public void drive(double driveSpeed, double robotRotation, double spin){
        if(robotRotation > 0.04 && (driveSpeed> 0.01 || driveSpeed < -0.01)){
            m_differentialDrive.tankDrive(driveSpeed, driveSpeed - Math.abs(robotRotation));
        }
        else if(robotRotation < -0.04 && (driveSpeed> 0.01 || driveSpeed < -0.01)){
            m_differentialDrive.tankDrive(driveSpeed - Math.abs(robotRotation), driveSpeed);
        }
        else if(driveSpeed> 0.01 || driveSpeed < -0.01){
            m_differentialDrive.tankDrive(driveSpeed, driveSpeed);
        }
         else if(spin < -0.04){
            m_differentialDrive.tankDrive(spin, -spin);
        }
        else if(spin > 0.04){
            m_differentialDrive.tankDrive(spin, -spin);
        }
        SmartDashboard.putNumber("rotation", robotRotation);
        SmartDashboard.putNumber("speed", driveSpeed);
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

    @Override
    public void periodic() { 
        if (Constants.DriveInfo.SHOW_DRIVETRAIN_IN_DASHBOARD){
            SmartDashboard.putNumber("Left Drive Speed", leftDriveMotorSpeed());
            SmartDashboard.putNumber("Right Drive Speed", rightDriveMotorSpeed());
            SmartDashboard.putNumber("Left Drive Encoder Value", getLeftDriveEncoderCount());
            SmartDashboard.putNumber("Right Drive Encoder Value", getRightDriveEncoderCount());
        }
    }
}
