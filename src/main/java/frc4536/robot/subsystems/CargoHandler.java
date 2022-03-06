package frc4536.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc4536.robot.Constants.CargoHandlerInfo;

public class CargoHandler extends SubsystemBase {
    private Spark m_cargoHandlerMotor;

    public CargoHandler() {
        m_cargoHandlerMotor = new Spark(CargoHandlerInfo.CARGO_HANDLER_MOTOR_ID);
        m_cargoHandlerMotor.setInverted(CargoHandlerInfo.CARGO_HANDLER_MOTOR_IS_INVERTED);
    }

    public void intakeCargo() {
        intakeCargo(CargoHandlerInfo.DEFAULT_INTAKE_CARGO_SPEED);
    }
    public void intakeCargo(double intakeSpeed) {
        m_cargoHandlerMotor.set(-Math.abs(intakeSpeed));
    }

    public void outputCargo() {
        outputCargo(CargoHandlerInfo.DEFAULT_OUTPUT_CARGO_SPEED);
    }
    public void outputCargo(double outputSpeed) {
        m_cargoHandlerMotor.set(Math.abs(outputSpeed));
    }

    public void stopRotating() {
        m_cargoHandlerMotor.set(0.0);
    }

    public double getCargoHandlerMotorsSpeed() {
        return m_cargoHandlerMotor.get();
    }

    @Override
    public void periodic() {
        if (CargoHandlerInfo.SHOW_CARGOHANDLER_IN_DASHBOARD) {
            SmartDashboard.putNumber("Cargo Handler Speed", getCargoHandlerMotorsSpeed());
        }
    }
}
