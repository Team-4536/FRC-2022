package frc4536.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.RobotInfo;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

public class DriveForward extends CommandBase{

    private DriveTrain m_driveTrain;
    private Gyroscope m_gyroscope;
    private PIDController m_pidController;
    private double kP;
    private double kI;
    private double kD;
    private double m_speed;
    private double m_distanceInFeet;
    private double m_goalPosition;

    public DriveForward(DriveTrain driveTrain, double speed, double distanceInFeet, Gyroscope gyroscope){
        kP = .0001;
        kI = 0;
        kD = 0;
        m_driveTrain = driveTrain;
        m_gyroscope = gyroscope;
        m_gyroscope.resetGyroscope();
        
        m_pidController = new PIDController(kP, kI, kD);
        m_distanceInFeet = distanceInFeet;

        m_goalPosition = (m_distanceInFeet * 12.0) * RobotInfo.CLICKS_PER_INCH + m_driveTrain.getLeftDriveEncoderCount();
        SmartDashboard.putNumber("distance in feet", m_distanceInFeet);
        SmartDashboard.putNumber("goal positiion", m_goalPosition);
        SmartDashboard.putNumber("left encoder count", m_driveTrain.getLeftDriveEncoderCount());
        SmartDashboard.putNumber("clicks per inch", RobotInfo.CLICKS_PER_INCH);

        addRequirements(m_driveTrain, m_gyroscope);


    }

    @Override
    public void initialize() {
        SmartDashboard.putString("command","drive forward");
    }

    @Override
    public void execute(){
        double turningValue = (m_gyroscope.getAngle() * 0.15);
        double speed = Math.abs(m_goalPosition - m_driveTrain.getLeftDriveEncoderCount()) * 0.0001;
        double dspeed = m_pidController.calculate(m_goalPosition = m_driveTrain.getLeftDriveEncoderCount());

        m_driveTrain.arcadeDriveNoSquaredInptus(0.6, turningValue);

        SmartDashboard.putNumber("encoder value drive", m_driveTrain.getLeftDriveEncoderCount());
        SmartDashboard.putNumber("target position", m_goalPosition);
        SmartDashboard.putNumber("Distance in feet", m_distanceInFeet);
        SmartDashboard.putNumber("speed test", speed);
        SmartDashboard.putNumber("d speed test", dspeed);
    }

    @Override
    public void end(boolean interrupted){
        m_driveTrain.stopDriving();
        SmartDashboard.putString("command", "forward is finished");
    }

    @Override
    public boolean isFinished() {
        return (m_driveTrain.getLeftDriveEncoderCount() >= m_goalPosition);
    }


    
}
