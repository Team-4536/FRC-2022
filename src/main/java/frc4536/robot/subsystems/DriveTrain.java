package frc4536.robot.subsystems;

import java.security.PublicKey;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;

public class DriveTrain extends SubsystemBase{
    private DifferentialDrive m_differentialDrive;
    private Encoder m_leftDrivEncoder;
    private Encoder m_rightDrivEncoder;

    public DriveTrain(){
        CANSparkMax frontLeftDriveMotor = new CANSparkMax(Constants.DriveInfo.FRONT_LEFT_DRIVE_MOTOR_ID, Constants.DriveInfo.TYPE_BRUSH_MOTOR);
        CANSparkMax frontRightDriveMotor = new CANSparkMax(Constants.DriveInfo.FRONT_RIGHT_DRIVE_MOTOR_ID, Constants.DriveInfo.TYPE_BRUSH_MOTOR);
        CANSparkMax backLeftDriveMotor = new CANSparkMax(Constants.DriveInfo.BACK_LEFT_DRIVE_MOTOR_ID, Constants.DriveInfo.TYPE_BRUSH_MOTOR);
        CANSparkMax backRightDriveMotor = new CANSparkMax(Constants.DriveInfo.BACK_RIGHT_DRIVE_MOTOR_ID, Constants.DriveInfo.TYPE_BRUSH_MOTOR);

        MotorControllerGroup leftMotorControllerGroup = new MotorControllerGroup(frontLeftDriveMotor, backLeftDriveMotor);
        MotorControllerGroup rightMotorControllerGroup = new MotorControllerGroup(frontRightDriveMotor, backRightDriveMotor);
        m_differentialDrive = new DifferentialDrive(leftMotorControllerGroup, rightMotorControllerGroup);
    } 
    public void arcadeDrive(double speed, double driveTrainRotation){
        m_differentialDrive.arcadeDrive(speed, driveTrainRotation);
        
    }
        public void rotateRobot(double rotationPower){
            m_differentialDrive.tankDrive(-rotationPower, rotationPower);


        }

     public void driveStraight(double speed){
        m_differentialDrive.arcadeDrive(speed, 0);

    }
   
   


}
