package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToUpper extends CommandBase {

    private final CargoArm m_cargoArm;

    public CargoArmToUpper(CargoArm cargoArm) {
        m_cargoArm = cargoArm;
        addRequirements(m_cargoArm);
    }

    @Override
    public void execute() {
        double currentShoulderPos = m_cargoArm.getShoulderPosition();
        double currentElbowPos = m_cargoArm.getElbowPosition();
        double targetElbowPos = (currentShoulderPos - CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION)
                * CargoArmInfo.CARGO_ARM_ELBOW_TO_SHOULDER_RATIO_ABOVE_INTERMEDIATE
                + CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION;

        double pidPowerShoulderValue = Math.min(Math.max(Math.abs(
            currentShoulderPos - CargoArmInfo.CARGOARM_SHOULDER_UPPER_POSITION)
             / CargoArmInfo.CARGO_ARM_PID_SHOULDER_VALUE, .2),
             CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);

        if (currentShoulderPos < CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
            if (currentElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
                m_cargoArm.moveElbow(-CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
            } else if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_INTER_RESTING_POSITION) {
                m_cargoArm.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
            } else {
                m_cargoArm.moveShoulder(CargoArmInfo.CARGO_ARM_SHOULDER_DEFAULT_POWER);
                m_cargoArm.moveElbow(0);
            }
        } else if (currentShoulderPos < CargoArmInfo.CARGOARM_SHOULDER_UPPER_POSITION) {
            m_cargoArm.moveShoulder(pidPowerShoulderValue);
            if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION) {
                m_cargoArm.moveElbow(
                        CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER * ((targetElbowPos - currentElbowPos) / 10000));
            }
        } else {
            m_cargoArm.moveShoulder(0.0);
            if (currentElbowPos < CargoArmInfo.CARGOARM_ELBOW_FINAL_POSITION) {
                m_cargoArm.moveElbow(CargoArmInfo.CARGO_ARM_ELBOW_DEFAULT_POWER);
            } else {
                m_cargoArm.moveElbow(0.0);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_cargoArm.moveElbow(0.0);
        m_cargoArm.moveShoulder(0.0);
    }
}