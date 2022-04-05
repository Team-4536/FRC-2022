// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot.Autos;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.commands.CargoArmToIntake;
import frc4536.robot.commands.CargoArmToResting;
import frc4536.robot.commands.CargoArmToUpper;
import frc4536.robot.commands.IntakeCargo;
import frc4536.robot.commands.OutputCargo;
import frc4536.robot.subsystems.CargoArm;
import frc4536.robot.subsystems.CargoHandler;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPractice extends SequentialCommandGroup {
  /** Creates a new AutoPractice. */
  public AutoPractice(CargoArm cargoArm, DriveTrain driveTrain, CargoHandler cargoHandler, Gyroscope gyroscope) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new CargoArmToUpper(cargoArm).deadlineWith(stopDriving(driveTrain)),
    new OutputCargo(cargoHandler).withTimeout(4.5).deadlineWith(stopDriving(driveTrain)),
    new CargoArmToResting(cargoArm).deadlineWith(stopDriving(driveTrain)),
    new RunCommand(() -> driveTrain.move(0.5, 0.5), driveTrain).withTimeout(1),
    new RunCommand(() -> driveTrain.spin(0.5, -0.5), driveTrain).withTimeout(1),
    new RunCommand(() -> driveTrain.move(0.5, 0.5), driveTrain).withTimeout(1),
    new CargoArmToIntake(cargoArm).deadlineWith(stopDriving(driveTrain)),
    new RunCommand(() -> driveTrain.move(0.5,0.5), driveTrain).alongWith(new IntakeCargo(cargoHandler).withTimeout(1),
    new CargoArmToUpper(cargoArm).deadlineWith(stopDriving(driveTrain)),
    new RunCommand(() -> driveTrain.spin(0.5, -0.5), driveTrain).withTimeout(1),
    new RunCommand(() -> driveTrain.move(0.5, 0.5), driveTrain).withTimeout(1),
    new OutputCargo(cargoHandler).withTimeout(1).deadlineWith(stopDriving(driveTrain))));
  }

private Command stopDriving(DriveTrain driveTrain) {
    return new RunCommand(()->driveTrain.stopDriving(),driveTrain);
}
}