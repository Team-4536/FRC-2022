package frc4536.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants.CargoArmInfo;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmToIntake extends CommandBase {

    private CargoArm m_cargoArm;

    double cargoArmElbowPos = m_cargoArm.getElbowPosition();
    double cargoArmShoulderPos = m_cargoArm.getShoulderPosition();

    public CargoArmToIntake(CargoArm cargoArm) {
        m_cargoArm = cargoArm;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_cargoArm);
    }

    @Override
    public void execute() {
        if (cargoArmShoulderPos > CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
            m_cargoArm.moveShoulder(-0.5);
            if (cargoArmElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
                m_cargoArm.moveElbow(-0.5);
            } else if (cargoArmElbowPos < CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
                m_cargoArm.moveElbow(0.5);
            } else {
                m_cargoArm.moveShoulder(0);
                m_cargoArm.moveElbow(0);
            }
        } else if (cargoArmShoulderPos < CargoArmInfo.CARGOARM_SHOULDER_INTERMEDIATE_POSITION) {
            m_cargoArm.moveShoulder(0.5);
            if (cargoArmElbowPos < CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
                m_cargoArm.moveElbow(0.5);
            } else if (cargoArmElbowPos > CargoArmInfo.CARGOARM_ELBOW_INTERMEDIATE_POSITION) {
                m_cargoArm.moveElbow(-0.5);
            } else {
                m_cargoArm.moveShoulder(0);
                m_cargoArm.moveElbow(0);
            }
        } else {
            m_cargoArm.moveShoulder(0);
            m_cargoArm.moveElbow(0);
        }

    }

    @Override
    public void end(boolean interrupted) {
        m_cargoArm.moveElbow(0);
        m_cargoArm.moveShoulder(0);
    }

}
