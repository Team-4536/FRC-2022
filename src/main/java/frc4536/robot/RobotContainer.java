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
import frc4536.robot.commands.CargoArmToResting;
import frc4536.robot.commands.CargoArmToUpper;
import frc4536.robot.commands.IntakeCargo;
import frc4536.robot.commands.OutputCargo;
import frc4536.robot.commands.ClimbForward;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final DriveTrain m_driveTrain;
  private final Gyroscope m_gyroscope;
  private final CargoHandler m_cargoHandler;
  private final Climber m_climber;
  private final CargoArm m_cargoArm;

  private final XboxController m_mechanismController;
  private final XboxController m_driveController;

 private final JoystickButton m_intakeCargoButton;
 private final JoystickButton m_outputCargoButton;

 private final JoystickButton m_runToUpperButton;
 private final JoystickButton m_runToIntakeButton;
 private final JoystickButton m_restingPosButton;

 private final JoystickButton m_climberButton;
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveTrain = new DriveTrain();
    m_gyroscope = new Gyroscope();
    m_cargoHandler = new CargoHandler();
    m_climber = new Climber();
    m_cargoArm = new CargoArm();

    //the controller id needs to be changed
    m_driveController = new XboxController(Constants.RobotInfo.MECHANISM_CONTROLLER_ID);
    m_mechanismController = new XboxController(Constants.RobotInfo.MECHANISM_CONTROLLER_ID);

   
    //mechanism controller
    m_outputCargoButton = new JoystickButton(m_mechanismController, XboxController.Button.kRightBumper.value);
    m_intakeCargoButton = new JoystickButton(m_mechanismController, XboxController.Button.kLeftBumper.value);

    m_runToUpperButton = new JoystickButton(m_mechanismController, XboxController.Button.kY.value);
    m_runToIntakeButton = new JoystickButton(m_mechanismController, XboxController.Button.kB.value);
    m_restingPosButton = new JoystickButton(m_mechanismController, XboxController.Button.kA.value);

    //drive controller
    m_climberButton = new JoystickButton(m_driveController, XboxController.Button.kX.value);
  

    configureButtonBindings();
    setDefaultCommands();




  }

  private void configureButtonBindings() {

    m_intakeCargoButton.whenHeld(new IntakeCargo(m_cargoHandler));
    m_outputCargoButton.whenHeld(new OutputCargo(m_cargoHandler));

    m_runToUpperButton.whenPressed(new CargoArmToUpper(m_cargoArm));
    m_restingPosButton.whenPressed(new CargoArmToResting(m_cargoArm));
    
    m_climberButton.whenHeld(new ClimbForward(m_climber));

  
    // this is where you define your buttons (do they need to be class level?)
    // and assign the appropriate Commands to them. example:
//    JoystickButton intakeCargoButton = 
//        new JoystickButton(m_mechanismController, XboxController.Button.kRightBumper.value);
//    intakeCargoButton.whenHeld(new IntakeCargo(m_cargoHandler)); 

  }

  private void setDefaultCommands() {

    m_cargoArm.setDefaultCommand(new CargoArmHoldInPlace(m_cargoArm));

    m_driveTrain.setDefaultCommand(new RunCommand(()-> 
         m_driveTrain.drive(m_mechanismController.getRightTriggerAxis() - m_mechanismController.getLeftTriggerAxis()  , m_mechanismController.getLeftX(), m_mechanismController.getRightX()),
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
