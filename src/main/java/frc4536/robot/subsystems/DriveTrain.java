package frc4536.robot.subsystems;

import java.security.PublicKey;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

import frc4536.robot.Constants.DriveInfo;;

public class DriveTrain extends SubsystemBase{
    private DifferentialDrive m_differentialDrive;
    private Encoder m_leftDriveEncoder;
    private Encoder m_rightDriveEncoder;
    private MotorControllerGroup m_leftMotorControllerGroup;
    private MotorControllerGroup m_rightMotorControllerGroup;

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

        m_leftDriveEncoder = new Encoder( DriveInfo.LEFT_DRIVE_MOTOR_ENCODER, DriveInfo.RIGHT_DRIVE_MOTOR_ENCODER, 
            DriveInfo.LEFT_DRIVE_MOTORS_ARE_INVERTED, DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE); 
        m_rightDriveEncoder = new Encoder( DriveInfo.LEFT_DRIVE_MOTOR_ENCODER, DriveInfo.RIGHT_DRIVE_MOTOR_ENCODER, 
            DriveInfo.RIGHT_DRIVE_MOTORS_ARE_INVERTED, DriveInfo.DRIVE_MOTOR_ENCODER_ENCODINGTYPE);  
    } 

    public void arcadeDrive(double speed, double driveTrainRotation){
        m_differentialDrive.arcadeDrive(speed, driveTrainRotation);
        
    }

    public void resetEncoders() {
        m_leftDriveEncoder.reset();
        m_rightDriveEncoder.reset();
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
   

    public double leftDriveMotorSpeed(){
        return m_leftMotorControllerGroup.get();
    }
    public double rightDriveMotorSpeed(){
        return m_rightMotorControllerGroup.get();
    }
}