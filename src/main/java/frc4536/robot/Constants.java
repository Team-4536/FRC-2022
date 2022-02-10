// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public final class Constants {

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
   }

   public static final class ClimberInfo {

      public static final int CLIMBER_MOTOR_ID = 5;

      public static final double CLIMBER_MOTOR_SPEED = 2.5;
      
      public static final MotorType CLIMBER_MOTOR_BRUSHED_TYPE = MotorType.kBrushless;

      public static final boolean CLIMBER_MOTOR_IS_INVERTED = true;

   
   }
}
