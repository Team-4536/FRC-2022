package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.subsystems.CargoArm;

public class CargoArmHoldInPlace extends CommandBase {

    private CargoArm m_cargoArm;

    private double m_cargoElbowTargetLocation;
    private double m_cargoShoulderTargetLocation;

    public CargoArmHoldInPlace(CargoArm cargoArm) {
        m_cargoArm = cargoArm;
        addRequirements(m_cargoArm);
    }

    @Override
    public void initialize() {
        m_cargoElbowTargetLocation = m_cargoArm.getElbowPosition();
        m_cargoShoulderTargetLocation = m_cargoArm.getShoulderPosition();

        m_cargoArm.moveElbow(0.0);
        m_cargoArm.moveShoulder(0.0);
    }

    @Override
    public void execute() {
        double elbowError = (m_cargoElbowTargetLocation - m_cargoArm.getElbowPosition()) / 10000;
        double shoulderError = m_cargoShoulderTargetLocation - m_cargoArm.getShoulderPosition();

        m_cargoArm.moveElbow(elbowError * 0.1);
        m_cargoArm.moveShoulder(shoulderError * 0.1);
    }

    @Override
    public void end(boolean interrupted) {
        m_cargoArm.moveElbow(0.0);
        m_cargoArm.moveShoulder(0.0);
    }
}
