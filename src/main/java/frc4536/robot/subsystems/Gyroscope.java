package frc4536.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyroscope extends SubsystemBase{
    private AHRS m_gyroscope;
   
    
    public Gyroscope(){
        m_gyroscope = new AHRS();
    }
    public float getPitch(){
        return m_gyroscope.getPitch();
    }
    public float getYaw(){
        return m_gyroscope.getYaw();
    }
    public float getRoll(){
        return m_gyroscope.getRoll();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pitch", getPitch());
        SmartDashboard.putNumber("Roll", getRoll());
        SmartDashboard.putNumber("Yaw", getYaw());
    }

    public void resetGyroscope(){
        m_gyroscope.reset();
        m_gyroscope.resetDisplacement();
    }
} 
