package frc4536.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4536.robot.Constants;

public class Gyroscope extends SubsystemBase{
    private AHRS m_gyroscope;
   
    public Gyroscope(){
        m_gyroscope = new AHRS();
    }
    public float getPitch(){
        return m_gyroscope.getPitch();
    }
    public float getRoll(){
        return m_gyroscope.getRoll();
    }
    public float getYaw(){
        return m_gyroscope.getYaw();
    }
    public void resetGyroscope(){
        m_gyroscope.reset();
        m_gyroscope.resetDisplacement();
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pitch", getPitch());
        SmartDashboard.putNumber("Roll", getRoll());
        SmartDashboard.putNumber("Yaw", getYaw());
    }
    public double getAngle(){
        return Math.IEEEremainder(m_gyroscope.getAngle(),360) * (Constants.GyroInfo.GYRO_IS_REVERSED ? -1.0 : 1.0);
    }
    public double getTurnRate(){
        return m_gyroscope.getRate() * (Constants.GyroInfo.GYRO_IS_REVERSED ? -1.0 : 1.0);
    }
} 

