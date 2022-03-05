// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc4536.robot.subsystems.CargoArm;
import frc4536.robot.subsystems.CargoHandler;
import frc4536.robot.subsystems.Climber;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;
import frc4536.robot.commands.CargoArmHoldInPlace;
import frc4536.robot.commands.CargoArmStop;
import frc4536.robot.commands.CargoArmToUpper;
import frc4536.robot.commands.IntakeCargo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain;
  private final Gyroscope m_gyroscope;
  private final CargoHandler m_cargoHandler;
  private final Climber m_climber;
  private final CargoArm m_cargoArm;

  private final XboxController m_mechanismController;
 // private final XboxController m_driveController;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveTrain = new DriveTrain();
    m_gyroscope = new Gyroscope();
    m_cargoHandler = new CargoHandler();
    m_climber = new Climber();
    m_cargoArm = new CargoArm();

    m_mechanismController = new XboxController(Constants.RobotInfo.MECHANISM_CONTROLLER_ID);
    //m_driveController = new XboxController(Constants.RobotInfo.DRIVE_CONTROLLER_ID);

    JoystickButton raiseShoulderButton = new JoystickButton(m_mechanismController, XboxController.Button.kA.value);
    JoystickButton lowerShoulderButton = new JoystickButton(m_mechanismController, XboxController.Button.kB.value);
    JoystickButton extendElbowButton = new JoystickButton(m_mechanismController, XboxController.Button.kY.value);
    JoystickButton retractElbowButton = new JoystickButton(m_mechanismController, XboxController.Button.kX.value);
    JoystickButton runToUpperButton = new JoystickButton(m_mechanismController, XboxController.Button.kRightBumper.value);

    configureButtonBindings();
    setDefaultCommands();

    m_cargoArm.setDefaultCommand(new CargoArmHoldInPlace(m_cargoArm));

    raiseShoulderButton.whenHeld(
      new RunCommand(() -> m_cargoArm.moveShoulder(0.25), m_cargoArm)
    );

    lowerShoulderButton.whenHeld(
      new RunCommand(() -> m_cargoArm.moveShoulder(-0.25), m_cargoArm)
    );

    extendElbowButton.whenHeld(
      new RunCommand(() -> m_cargoArm.moveElbow(0.5), m_cargoArm)
    );

    retractElbowButton.whenHeld(
      new RunCommand(() -> m_cargoArm.moveElbow(-0.5), m_cargoArm)
    );

    runToUpperButton.whenHeld(
      new CargoArmToUpper(m_cargoArm)
    );

    
  }

  private void configureButtonBindings() {
    // this is where you define your buttons (do they need to be class level?)
    // and assign the appropriate Commands to them. example:
//    JoystickButton intakeCargoButton = 
//        new JoystickButton(m_mechanismController, XboxController.Button.kRightBumper.value);
//    intakeCargoButton.whenHeld(new IntakeCargo(m_cargoHandler)); 

  }

  private void setDefaultCommands() {

  /*  m_driveTrain.setDefaultCommand(new RunCommand(()-> 
         m_driveTrain.arcadeDrive(-m_driveController.getRightY(), m_driveController.getLeftX()), 
         m_driveTrain)); */

  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
