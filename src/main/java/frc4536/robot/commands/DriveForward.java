package frc4536.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.RobotInfo;
import frc4536.robot.subsystems.DriveTrain;

public class DriveForward extends CommandBase{

    private DriveTrain m_driveTrain;
    private double m_speed;
    private double m_distanceInFeet;
    private Timer m_timer;
    private double m_goalPosition;

    public DriveForward(DriveTrain driveTrain, double speed, double distanceInFeet){
        m_driveTrain = driveTrain;
        m_goalPosition = (distanceInFeet * 12) * RobotInfo.CLICKS_PER_INCH + m_driveTrain.getLeftDriveEncoderCount();
        addRequirements(m_driveTrain);
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("command","drive forward");
        m_timer = new Timer();
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute(){
        m_driveTrain.drive(0.6, 0, 0);
    }

    @Override
    public void end(boolean interrupted){
        m_driveTrain.stopDriving();
        SmartDashboard.putString("command", "forward is finished");
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= 1.0;
    }


    
}
