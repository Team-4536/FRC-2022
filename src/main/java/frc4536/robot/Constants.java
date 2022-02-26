// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4536.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
public final class Constants {
    public final static class CargoArmInfo{
        public final static int CARGO_ARM_ELBOW_ID = 1;
        public final static int CARGO_ARM_SHOULDER_ID = 2;

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
    }
}
