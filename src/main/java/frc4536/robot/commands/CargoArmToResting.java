package frc4536.robot.commands;

import edu.wpi.first.hal.simulation.ConstBufferCallback;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToResting extends CommandBase {

    private final CargoArm m_cargoArm;
    private boolean m_elbowHasGoneHome;
    private boolean m_shoulderHasGoneHome;

    public CargoArmToResting(CargoArm cargoArm) {
        m_cargoArm = cargoArm;
        m_elbowHasGoneHome = false;
        m_shoulderHasGoneHome = false;
        addRequirements(m_cargoArm);
        
    }

    @Override
    public void initialize() {
        m_elbowHasGoneHome = false;
        m_shoulderHasGoneHome = false;
    }

    @Override
    public void execute() {
        double currentShoulderPos = m_cargoArm.getShoulderPosition();
        double currentElbowPos = m_cargoArm.getElbowPosition();

        if (!m_elbowHasGoneHome) {
            if (m_cargoArm.elbowIsHome()) {
                m_elbowHasGoneHome = true;
            }
        }

        if (!m_shoulderHasGoneHome) {
            if (m_cargoArm.shoulderIsHome()) {
                m_shoulderHasGoneHome = true;
            }
        }


        double targetElbowPos = (currentShoulderPos - CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION)
                * CargoArmInfo.CARGO_ARM_ELBOW_TO_SHOULDER_RATIO_ABOVE_INTERMEDIATE
                + CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION;

        double pidPowerElbowValue = -Math.min(Math.max((CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER
                * Math.abs(CargoArmInfo.CARGOARM_ELBOW_RESTING_POSITION - 1000 - currentElbowPos))
                / CargoArmInfo.CARGO_ARM_PID_ELBOW_VALUE, 0.2),
                CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
        double pidPowerShoulderValue = -Math.min(Math.max((CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER
                * Math.abs(CargoArmInfo.CARGOARM_SHOULDER_RESTING_POSITION - currentShoulderPos))
                / CargoArmInfo.CARGO_ARM_PID_SHOULDER_VALUE, 0.2),
                CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);

        if (currentShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
            m_cargoArm.moveShoulder(-CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
            SmartDashboard.putBoolean("has gone home", m_elbowHasGoneHome);
            m_cargoArm.moveElbow(
                    CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER * ((targetElbowPos - currentElbowPos) / (3000)));
        } else if (!m_shoulderHasGoneHome) {
            if (!m_elbowHasGoneHome) {
                m_cargoArm.moveElbow(pidPowerElbowValue);
            } else
                m_cargoArm.moveElbow(0.0);
            m_cargoArm.moveShoulder(pidPowerShoulderValue);
        } else {
            m_cargoArm.moveShoulder(0.0);
            if (!m_elbowHasGoneHome) {
                m_cargoArm.moveElbow(pidPowerElbowValue);
            } else {
                m_cargoArm.moveElbow(0.0);
            }
        }
        SmartDashboard.putBoolean("shoulder has gone home", m_shoulderHasGoneHome);
    }

    @Override
    public void end(boolean interrupted) {
        m_cargoArm.moveElbow(0.0);
        m_cargoArm.moveShoulder(0.0);
    }


}