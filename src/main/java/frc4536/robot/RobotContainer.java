// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import frc4536.robot.commands.Autos.*;
import frc4536.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain;
  private final NetworkTableEntry m_xInitial, m_yInitial;
  private final SendableChooser<Autonomous> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  Trajectory t_poseCheck;
  Trajectory t_blueAutoOneOne;
  Trajectory t_blueAutoTwoOne;
  Trajectory t_blueAutoThreeOne;
  Trajectory t_blueAutoThreeTwo;
  Trajectory t_redAutoOneOne;
  Trajectory t_redAutoTwoOne;
  Trajectory t_redAutoThreeOne;
  Trajectory t_redAutoThreeTwo;

  public RobotContainer() {

    m_driveTrain = new DriveTrain();

    // Configure the button bindings
    configureButtonBindings();
    generatePoseCheckTrajectory();
    generateBlueAuto1Trajectory1();
    generateBlueAuto2Trajectory1();
    generateBlueAuto3Trajectory1();
    generateBlueAuto3Trajectory2();
    generateRedAuto1Trajectory1();
    generateRedAuto2Trajectory1();
    generateRedAuto3Trajectory1();
    generateRedAuto3Trajectry2();

    ShuffleboardTab auto = Shuffleboard.getTab("Autonomous");
    
    m_xInitial = auto.add("Initial X", 0.0).getEntry();
    m_yInitial = auto.add("Initial Y", 0.0).getEntry();
    m_chooser.addOption("Blue Auto One", Autonomous.BLUE_AUTO_ONE);
    m_chooser.addOption("Blue Auto Two", Autonomous.BLUE_AUTO_TWO);
    m_chooser.addOption("Blue Auto Three", Autonomous.BLUE_AUTO_THREE);
    m_chooser.addOption("Red Auto One", Autonomous.RED_AUTO_ONE);
    m_chooser.addOption("Red Auto Two", Autonomous.RED_AUTO_TWO);
    m_chooser.addOption("Red Auto Three", Autonomous.RED_AUTO_THREE);
    m_chooser.addOption("Pose Check Auto", Autonomous.POSECHECK_AUTO);
  }
  private void generatePoseCheckTrajectory(){
    var poseCheckWaypoints = new ArrayList<Pose2d>();
    poseCheckWaypoints.add(Poses.SCORE_ONE);
    poseCheckWaypoints.add(Poses.BALL_ONE);
    poseCheckWaypoints.add(Poses.BALL_TWO);
    poseCheckWaypoints.add(Poses.BALL_THREE);
    poseCheckWaypoints.add(Poses.BALL_FOUR);
    poseCheckWaypoints.add(Poses.SCORE_TWO);
    poseCheckWaypoints.add(Poses.BALL_FIVE);
    poseCheckWaypoints.add(Poses.BALL_SIX);
    poseCheckWaypoints.add(Poses.BALL_SEVEN);
    poseCheckWaypoints.add(Poses.BALL_EIGHT);
    poseCheckWaypoints.add(Poses.SCORE_THREE);
    poseCheckWaypoints.add(Poses.BALL_NINE);
    poseCheckWaypoints.add(Poses.BALL_TEN);
    poseCheckWaypoints.add(Poses.SCORE_FOUR);
    poseCheckWaypoints.add(Poses.BALL_ELEVEN);
    poseCheckWaypoints.add(Poses.BALL_TWELVE);
    t_poseCheck = TrajectoryGenerator.generateTrajectory(poseCheckWaypoints, m_driveTrain.getConfig());
  }

  private void generateBlueAuto1Trajectory1(){
    var blueOneOneWaypoints = new ArrayList<Pose2d>();
    blueOneOneWaypoints.add(Poses.BALL_TWO);
    blueOneOneWaypoints.add(Poses.BALL_THREE);
    blueOneOneWaypoints.add(Poses.SCORE_ONE);
    t_blueAutoOneOne = TrajectoryGenerator.generateTrajectory(blueOneOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateBlueAuto2Trajectory1(){
    var blueTwoOneWaypoints = new ArrayList<Pose2d>();
    blueTwoOneWaypoints.add(Poses.BALL_FIVE);
    blueTwoOneWaypoints.add(Poses.SCORE_TWO);
    t_blueAutoTwoOne = TrajectoryGenerator.generateTrajectory(blueTwoOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateBlueAuto3Trajectory1(){
    var blueThreeOneWaypoints = new ArrayList<Pose2d>();
    blueThreeOneWaypoints.add(Poses.BALL_FIVE);
    blueThreeOneWaypoints.add(Poses.SCORE_TWO);
    t_blueAutoThreeOne = TrajectoryGenerator.generateTrajectory(blueThreeOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateBlueAuto3Trajectory2(){
    var blueThreeTwoWaypoints = new ArrayList<Pose2d>();
    blueThreeTwoWaypoints.add(Poses.BALL_THREE);
    blueThreeTwoWaypoints.add(Poses.BALL_TWO);
    blueThreeTwoWaypoints.add(Poses.SCORE_TWO);
    t_blueAutoThreeTwo = TrajectoryGenerator.generateTrajectory(blueThreeTwoWaypoints, m_driveTrain.getConfig());
  }

  private void generateRedAuto1Trajectory1(){
    var redOneOneWaypoints = new ArrayList<Pose2d>();
    redOneOneWaypoints.add(Poses.BALL_EIGHT);
    redOneOneWaypoints.add(Poses.BALL_NINE);
    redOneOneWaypoints.add(Poses.SCORE_THREE);
    t_redAutoOneOne = TrajectoryGenerator.generateTrajectory(redOneOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateRedAuto2Trajectory1(){
    var redTwoOneWaypoints = new ArrayList<Pose2d>();
    redTwoOneWaypoints.add(Poses.BALL_ELEVEN);
    redTwoOneWaypoints.add(Poses.SCORE_FOUR);
    t_redAutoTwoOne = TrajectoryGenerator.generateTrajectory(redTwoOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateRedAuto3Trajectory1(){
    var redThreeOneWaypoints = new ArrayList<Pose2d>();
    redThreeOneWaypoints.add(Poses.BALL_ELEVEN);
    redThreeOneWaypoints.add(Poses.SCORE_FOUR);
    t_redAutoThreeOne = TrajectoryGenerator.generateTrajectory(redThreeOneWaypoints, m_driveTrain.getConfig());
  }

  private void generateRedAuto3Trajectry2(){
    var redThreeTwoWaypoints = new ArrayList<Pose2d>();
    redThreeTwoWaypoints.add(Poses.BALL_NINE);
    redThreeTwoWaypoints.add(Poses.BALL_EIGHT);
    redThreeTwoWaypoints.add(Poses.SCORE_FOUR);
    t_redAutoThreeTwo = TrajectoryGenerator.generateTrajectory(redThreeTwoWaypoints, m_driveTrain.getConfig());
  }

  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  public Command generateAutoCommands(Autonomous chose, Pose2d initialPose ){
    switch(chose){
      case RED_AUTO_ONE:
          return new RedAutoOne(m_driveTrain, initialPose, t_redAutoOneOne);
      case RED_AUTO_TWO:
          return new RedAutoTwo(m_driveTrain, initialPose, t_redAutoTwoOne);
      case RED_AUTO_THREE:
          return new RedAutoThree(m_driveTrain, initialPose, t_redAutoThreeOne, t_redAutoThreeTwo);
      case BLUE_AUTO_ONE:
          return new BlueAutoOne(m_driveTrain, initialPose, t_blueAutoOneOne);
      case BLUE_AUTO_TWO:
          return new BlueAutoTwo(m_driveTrain, initialPose, t_blueAutoTwoOne);
      case BLUE_AUTO_THREE:
          return new BlueAutoThree(m_driveTrain, initialPose, t_blueAutoThreeOne, t_blueAutoThreeTwo);
      case POSECHECK_AUTO:
          return new PoseCheckAuto(m_driveTrain, initialPose, t_poseCheck);
      default:
          return new PoseCheckAuto(m_driveTrain, initialPose, t_poseCheck);

    }
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
  private enum Autonomous{
    RED_AUTO_ONE,
    RED_AUTO_TWO,
    RED_AUTO_THREE,
    BLUE_AUTO_ONE,
    BLUE_AUTO_TWO,
    BLUE_AUTO_THREE,
    POSECHECK_AUTO;
  }
}
