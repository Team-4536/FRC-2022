package frc4536.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.subsystems.CargoArm;
import frc4536.robot.subsystems.CargoHandler;
import frc4536.robot.subsystems.DriveTrain;

public class autostryone extends SequentialCommandGroup {

    public autostryone  (DriveTrain driveTrain, CargoArm cargoArm, CargoHandler cargoHandler) {
        RunCommand dontMove = new RunCommand(()->driveTrain.stopDriving(),driveTrain);
    }
}
