package frc4536.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Poses {
    public static final Pose2d
    // putting zero as degrees is just a place holder, they will probably need to be changed for a functioning auto
    BALL_ONE = new Pose2d(Units.inchesToMeters(33.0), Units.inchesToMeters(-144.5), Rotation2d.fromDegrees(0)),
    BALL_TWO = new Pose2d(Units.inchesToMeters(-25.2), Units.inchesToMeters(-146.14), Rotation2d.fromDegrees(0)),
    BALL_THREE = new Pose2d(Units.inchesToMeters(-124.4), Units.inchesToMeters(-83.7), Rotation2d.fromDegrees(0)),
    BALL_FOUR = new Pose2d(Units.inchesToMeters(-145.38), Units.inchesToMeters(-29.3), Rotation2d.fromDegrees(0)),
    BALL_FIVE = new Pose2d(Units.inchesToMeters(-126.7), Units.inchesToMeters(77.0), Rotation2d.fromDegrees(0)),
    BALL_SIX = new Pose2d(Units.inchesToMeters(-81.66), Units.inchesToMeters(120.28), Rotation2d.fromDegrees(0)),
    BALL_SEVEN = new Pose2d(Units.inchesToMeters(-33.0), Units.inchesToMeters(144.5), Rotation2d.fromDegrees(0)),
    BALL_EIGHT = new Pose2d(Units.inchesToMeters(25.2), Units.inchesToMeters(146.14), Rotation2d.fromDegrees(0)),
    BALL_NINE = new Pose2d(Units.inchesToMeters(124.4), Units.inchesToMeters(83.7), Rotation2d.fromDegrees(0)),
    BALL_TEN = new Pose2d(Units.inchesToMeters(145.38), Units.inchesToMeters(29.3), Rotation2d.fromDegrees(0)),
    BALL_ELEVEN = new Pose2d(Units.inchesToMeters(126.7), Units.inchesToMeters(-77.0), Rotation2d.fromDegrees(0)),
    BALL_TWELVE = new Pose2d(Units.inchesToMeters(81.66), Units.inchesToMeters(-120.28), Rotation2d.fromDegrees(0)),
    /* 
    score is just a guess on where the robot will score from, they were placed right up against the hub in the middle of the fender, 
    this may need to change when we have more info on the output mechanism
    */
    SCORE_ONE = new Pose2d(Units.inchesToMeters(-11.6), Units.inchesToMeters(-39.11), Rotation2d.fromDegrees(0)),
    SCORE_TWO = new Pose2d(Units.inchesToMeters(-36.47), Units.inchesToMeters(37.9), Rotation2d.fromDegrees(0)),
    SCORE_THREE = new Pose2d(Units.inchesToMeters(11.6), Units.inchesToMeters(39.11), Rotation2d.fromDegrees(0)),
    SCORE_FOUR = new Pose2d(Units.inchesToMeters(36.47), Units.inchesToMeters(-37.9), Rotation2d.fromDegrees(0))
    ;
}

