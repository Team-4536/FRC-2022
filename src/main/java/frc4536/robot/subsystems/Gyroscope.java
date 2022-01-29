package frc4536.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyroscope extends SubsystemBase{
    private AHRS m_gyroscope;
    
    public class GyroscopeRotationalData{
       
        public GyroscopeRotationalData(){
            float yaw;
            float roll;
            float pitch;
            

        }
    }
    
    public Gyroscope(){
        m_gyroscope = new AHRS();
    }

    public void getGyroscopeValues(){
        GyroscopeRotationalData.yaw = Gyroscope.getYaw();
        GyroscopeRotationalData.pitch = Gyroscope.getPitch();
        GyroscopeRotationalData.roll = Gyroscope.getRoll();

    }
} 

