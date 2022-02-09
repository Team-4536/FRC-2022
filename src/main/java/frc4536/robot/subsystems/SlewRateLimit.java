package frc4536.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.filter.Debouncer;

public class SlewRateLimit extends SubsystemBase {
    private final SlewRateLimiter m_SlewRateLimiter;
    private final Debouncer m_Debouncer;

    public SlewRateLimit(){
        m_SlewRateLimiter = new SlewRateLimiter(0.5);
        m_Debouncer = new Debouncer(0.2);

    }
    
}
