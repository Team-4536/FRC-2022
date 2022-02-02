// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc4536.robot.subsystems.DriveTrain;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain;
  private final XboxController m_mechanismController;
  private final XboxController m_drivController;
  private final JoystickButton m_intakeCargoButton;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveTrain = new DriveTrain();
    // Configure the button bindings
    configureButtonBindings();

    m_mechanismController = new XboxController(Constants.RobotInfo.MECHANISM_CONTROLLER_ID);
    m_drivController = new XboxController(Constants.RobotInfo.DRIVE_CONTROLLER_ID);

    m_intakeCargoButton = new JoystickButton(m_mechanismController, XboxController.Button.kA.value);
   // m_intakeCargoButton.whenHeld(new IntakeCargo());

    m_driveTrain.setDefaultCommand
        (new RunCommand(()-> m_driveTrain.arcadeDrive(-m_drivController.getRightY(), m_drivController.getLeftX()), m_driveTrain));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    
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
