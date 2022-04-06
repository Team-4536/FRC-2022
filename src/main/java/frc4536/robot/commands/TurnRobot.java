package frc4536.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

public class TurnRobot extends CommandBase{

    private DriveTrain m_driveTrain;
    private Gyroscope m_gyroscope;
    private PIDController m_pidController;
    private double kP;
    private double kI;
    private double kD;
    private double m_goalDegree;

    public TurnRobot(DriveTrain driveTrain, double goalDegree, Gyroscope gyroscope){

        kP = .005;
        kI = 0;
        kD = 0;
        m_pidController = new PIDController(kP, kI, kD);

        m_driveTrain = driveTrain;
        m_gyroscope = gyroscope;

        m_gyroscope.resetGyroscope();

        m_goalDegree = -goalDegree;
        
    }

    @Override
    public void initialize() {
        m_gyroscope.resetGyroscope();
    }

    @Override
    public void execute() {
        
        double pidSpeed = Math.min(m_pidController.calculate(-m_goalDegree + m_gyroscope.getAngle()), .6);

        m_driveTrain.tankDriveNoSquaredInputs(.5, -.5);

        SmartDashboard.putNumber("pid turn", pidSpeed);
        SmartDashboard.putNumber("target value angle", m_goalDegree);
        SmartDashboard.putNumber("current value angle", m_gyroscope.getAngle());

    }

    @Override
    public boolean isFinished() {
        return m_goalDegree >= m_gyroscope.getAngle();
    }




    
}
