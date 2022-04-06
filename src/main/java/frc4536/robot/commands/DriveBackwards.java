package frc4536.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    public DriveBackwards(DriveTrain driveTrain, Gyroscope gyroscope, double distanceInFeet){

        kP = 0.0001;
        kI = 0.000062;
        kD = 0.00002;
        m_pidController = new PIDController(kP, kI, kD);

        m_driveTrain = driveTrain;
        m_gyroscope = gyroscope;
        
        m_gyroscope.resetGyroscope();

        m_distanceInFeet = distanceInFeet;
        m_goalPosition = -(m_distanceInFeet * 12.0) * RobotInfo.CLICKS_PER_INCH + m_driveTrain.getLeftDriveEncoderCount();

        addRequirements(m_driveTrain, m_gyroscope);

    }

    @Override
    public void initialize() {
        m_goalPosition = -(m_distanceInFeet * 12.0) * RobotInfo.CLICKS_PER_INCH + m_driveTrain.getLeftDriveEncoderCount();
    }

    @Override
    public void execute() {

        double turningValue = (m_gyroscope.getAngle() * 0.2);
        double dspeed = -Math.min(-m_pidController.calculate(-m_goalPosition + m_driveTrain.getLeftDriveEncoderCount()), .6);

        m_driveTrain.arcadeDriveNoSquaredInputs(dspeed, -turningValue);

        SmartDashboard.putNumber("encoder value drive", m_driveTrain.getLeftDriveEncoderCount());
        SmartDashboard.putNumber("target position", m_goalPosition);
        SmartDashboard.putNumber("Distance in feet", m_distanceInFeet);
        SmartDashboard.putNumber("d speed test", dspeed);
        
    }

    @Override
    public boolean isFinished() {
        return m_driveTrain.getLeftDriveEncoderCount() <= m_goalPosition;
    }
    
}
