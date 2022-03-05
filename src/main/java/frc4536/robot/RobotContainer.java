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

import frc4536.robot.subsystems.CargoHandler;
import frc4536.robot.subsystems.Climber;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Gyroscope;

import frc4536.robot.commands.IntakeCargo;
import frc4536.robot.commands.ClimbForward;

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

  private final XboxController m_mechanismController;
  private final XboxController m_driveController;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveTrain = new DriveTrain();
    m_gyroscope = new Gyroscope();
    m_cargoHandler = new CargoHandler();
    m_climber = new Climber();

    m_mechanismController = new XboxController(Constants.RobotInfo.MECHANISM_CONTROLLER_ID);
    m_driveController = new XboxController(Constants.RobotInfo.DRIVE_CONTROLLER_ID);

    configureButtonBindings();
    setDefaultCommands();
    XboxController mechanismController = new XboxController(0);
    JoystickButton climberButton = new JoystickButton(mechanismController, XboxController.Button.kA.value);
    climberButton.whenHeld(new ClimbForward(m_climber));
  }

  private void configureButtonBindings() {
    // this is where you define your buttons (do they need to be class level?)
    // and assign the appropriate Commands to them. example:
//    JoystickButton intakeCargoButton = 
//        new JoystickButton(m_mechanismController, XboxController.Button.kRightBumper.value);
//    intakeCargoButton.whenHeld(new IntakeCargo(m_cargoHandler)); 

  }

  private void setDefaultCommands() {

    m_driveTrain.setDefaultCommand(new RunCommand(()-> 
         m_driveTrain.arcadeDrive(-m_mechanismController.getRightY(), m_mechanismController.getLeftX()), 
         m_driveTrain));

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
