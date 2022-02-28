// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public final class Constants {
    public final static class CargoArmInfo{
        public final static int CARGO_ARM_ELBOW_ID = 1;
        public final static int CARGO_ARM_SHOULDER_ID = 6;

        public final static int CHANNEL_A_CARGO_ARM_SHOULDER_ENCODER = 4;
        public final static int CHANNEL_B_CARGO_ARM_SHOULDER_ENCODER = 5;
        public final static boolean CARGO_ARM_SHOULDER_ENCODER_IS_INVERTED = true;
        public final static int CHANNEL_A_CARGO_ARM_ELBOW_ENCODER = 9;
        public final static int CHANNEL_B_CARGO_ARM_ELBOW_ENCODER = 10;
        public final static boolean CARGO_ARM_ELBOW_ENCODER_IS_INVERTED = true; 

        public final static double CARGO_ARM_ELBOW_DEFAULT_POWER = 0.5;
        public final static double CARGO_ARM_SHOULDER_DEFAULT_POWER = 0.5;

        public final static CounterBase.EncodingType CARGO_ARM_SHOULDER_ENCODER_TYPE = EncodingType.k2X;
        public final static CounterBase.EncodingType CARGO_ARM_ELBOW_ENCODER_TYPE = EncodingType.k2X;

        public final static MotorType CARGOARM_SHOULDER_BRUSHED_TYPE = MotorType.kBrushless;
    }
   public static final class DriveInfo {
      public static final int LEFT_FRONT_DRIVE_MOTOR_ID = 1;
      public static final int LEFT_REAR_DRIVE_MOTOR_ID = 2;
      public static final int RIGHT_FRONT_DRIVE_MOTOR_ID = 4;
      public static final int RIGHT_REAR_DRIVE_MOTOR_ID = 3;
      public static final MotorType DRIVE_MOTOR_BRUSHED_TYPE = MotorType.kBrushed;
 
      public static final boolean LEFT_DRIVE_MOTORS_ARE_INVERTED = true;
      public static final boolean RIGHT_DRIVE_MOTORS_ARE_INVERTED = false;

      public static final double DIFFERENTIAL_DRIVE_DEADBAND = 0.08;

      public static final int LEFT_DRIVE_ENCODER_CHANNEL_A = 0;
      public static final int LEFT_DRIVE_ENCODER_CHANNEL_B = 1;
      public static final int RIGHT_DRIVE_ENCODER_CHANNEL_A = 2;
      public static final int RIGHT_DRIVE_ENCODER_CHANNEL_B = 3;
      
      public static final EncodingType DRIVE_MOTOR_ENCODER_ENCODINGTYPE = EncodingType.k2X;

      public static final boolean LEFT_DRIVE_ENCODER_IS_INVERTED = false;
      public static final boolean RIGHT_DRIVE_ENCODER_IS_INVERTED = true;
   }
   public static final class RobotInfo {
      public static int MECHANISM_CONTROLLER_ID = 0;
      public static int DRIVE_CONTROLLER_ID = 1;

      private static final double DRIVE_WHEEL_DIAMETER = 6.0;
      private static final double WHEEL_CIRCUMFERENCE_IN_INCHES = DRIVE_WHEEL_DIAMETER * Math.PI;
      private static final double DRIVE_ENCODER_CLICKS_PER_ROTATION = 1.0;
      public static final double CLICKS_PER_INCH = WHEEL_CIRCUMFERENCE_IN_INCHES / DRIVE_ENCODER_CLICKS_PER_ROTATION;

      private static final double INCHES_WIDTH_OF_AXLE = 22.0;
      private static final double INCHES_LENGTH_OF_AXLE = 24.0;
      private static final double DIAGONAL_INCHES_BETWEEN_AXES = Math
            .sqrt((INCHES_WIDTH_OF_AXLE * INCHES_WIDTH_OF_AXLE) + (INCHES_LENGTH_OF_AXLE * INCHES_LENGTH_OF_AXLE));
      private static final double ROBOT_ROTATION_CIRCUMFERENCE = DIAGONAL_INCHES_BETWEEN_AXES * Math.PI;
      public static final double DRIVE_ENCODER_CLICKS_PER_DEGREE = ROBOT_ROTATION_CIRCUMFERENCE / CLICKS_PER_INCH / 360;
   }
   public static final class GyroInfo {
      public static final boolean Gyro_IS_REVERSED = true;
      // TODO change after testing
   }
   public static final class CargoHandlerInfo {
      public static final int CARGO_HANDLER_MOTOR_ID = 0;
      public static final double DEFAULT_INTAKE_CARGO_SPEED = -0.8;
      public static final double DEFAULT_OUTPUT_CARGO_SPEED = 0.8;
      public static final boolean CARGO_HANDLER_MOTOR_IS_INVERTED = true;
   }
   public static final class ClimberInfo {
      public static final int CLIMBER_MOTOR_ID = 5;
      public static final MotorType CLIMBER_MOTOR_BRUSHED_TYPE = MotorType.kBrushless;
      public static final boolean CLIMBER_MOTOR_IS_INVERTED = true;

      public static final int LIMIT_SWITCH_ALPHA_MOTOR_ID = 8;
      public static final int LIMIT_SWITCH_BETA_MOTOR_ID = 9;

      public static final double CLIMBER_MOTOR_SPEED = 0.25;
   }
}
