package frc4536.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyroscope extends SubsystemBase{
    private AHRS m_gyroscope;
    private GyroscopeRotationalData m_gyroscopeRotationData;

 
    public class GyroscopeRotationalData{
       
        public float pitch;
        public float yaw;
        public float roll;

        public GyroscopeRotationalData(){}
            

        }
    
    
    public Gyroscope(){
        m_gyroscope = new AHRS();
    }
/*
    public void setGyroscopeValues(){
        m_gyroscopeRotationData.yaw = m_gyroscope.getYaw();
        m_gyroscopeRotationData.pitch = m_gyroscope.getPitch();
        m_gyroscopeRotationData.roll = m_gyroscope.getRoll();

    }
    */

    public void resetGyroscope(){
        m_gyroscope.reset();
        m_gyroscope.resetDisplacement();
    }
} 

