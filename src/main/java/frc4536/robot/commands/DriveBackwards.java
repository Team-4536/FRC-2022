package frc4536.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.RobotInfo;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

public class DriveBackwards extends CommandBase{

    private DriveTrain m_driveTrain;
    private Gyroscope m_gyroscope;
    private PIDController m_pidController;

    private double kP;
    private double kI;
    private double kD;

    private double m_distanceInFeet;
    private double m_goalPosition;

    public DriveBackwards(DriveTrain driveTrain, Gyroscope gyroscope, double distanceinFeet){

        kP = .0001;
        kI = .000062;
        kD = .00002;

        m_driveTrain = driveTrain;
        m_gyroscope = gyroscope;
        
        m_gyroscope.resetGyroscope();

        m_distanceInFeet = distanceinFeet;
        m_goalPosition = (distanceinFeet * 12.0) * RobotInfo.CLICKS_PER_INCH + m_driveTrain.getLeftDriveEncoderCount();

    }
    
}
