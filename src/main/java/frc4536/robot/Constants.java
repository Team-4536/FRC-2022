// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

// TODO: find clicks per wheel rotation to calculate clicks per inch and clicks per degree

    public static final class RobotInfo{
        private static final double DRIVE_WHEEL_DIAMETER = 6.0;
        private static final double WHEEL_CIRCUMFERENCE_IN_INCHES = DRIVE_WHEEL_DIAMETER * Math.PI;
        private static final double DRIVE_ENCODER_CLICKS_PER_ROTATION = 1.0;
        public static final double CLICKS_PER_INCH = WHEEL_CIRCUMFERENCE_IN_INCHES / DRIVE_ENCODER_CLICKS_PER_ROTATION;

       private static final double INCHES_WIDTH_OF_AXLE = 22.0;
       private static final double INCHES_LENGTH_OF_AXLE = 24.0;
        private static final double DIAGONAL_INCHES_BETWEEN_AXES = Math.sqrt((INCHES_WIDTH_OF_AXLE * INCHES_WIDTH_OF_AXLE) + (INCHES_LENGTH_OF_AXLE * INCHES_LENGTH_OF_AXLE)) ;
        private static final double ROBOT_ROTATION_CIRCUMFERENCE = DIAGONAL_INCHES_BETWEEN_AXES * Math.PI;
        public static final double DRIVE_ENCODER_CLICKS_PER_DEGREE = ROBOT_ROTATION_CIRCUMFERENCE / CLICKS_PER_INCH / 360;
    }
    

}


