// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDrivetrain;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  private TalonFX m_motorFrontLeft = new TalonFX(DrivetrainConstants.kFrontLeftID);
  private TalonFX m_motorFrontRight = new TalonFX(DrivetrainConstants.kFrontRightID);
  private TalonFX m_motorBottomLeft = new TalonFX(DrivetrainConstants.kBottomLeftID);
  private TalonFX m_motorBottomRight = new TalonFX(DrivetrainConstants.kBottomRightID);
 
  public Drivetrain() {
    TalonFXConfiguration m_FrontLeftConfig = new TalonFXConfiguration();
    TalonFXConfiguration m_FrontRightConfig = new TalonFXConfiguration();
    TalonFXConfiguration m_BottomLeftConfig = new TalonFXConfiguration();
    TalonFXConfiguration m_BottomRightConfig = new TalonFXConfiguration();

    m_FrontLeftConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_FrontRightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_BottomLeftConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_BottomRightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    m_FrontLeftConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    m_FrontRightConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    m_BottomLeftConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    m_BottomRightConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    m_motorFrontLeft.getConfigurator().apply(m_FrontLeftConfig);
    m_motorFrontRight.getConfigurator().apply(m_FrontRightConfig);
    m_motorBottomLeft.getConfigurator().apply(m_BottomLeftConfig);
    m_motorBottomRight.getConfigurator().apply(m_BottomRightConfig);
  }

    public void setLeftVoltage(double voltage) {
      m_motorFrontLeft.set(voltage);
      m_motorBottomLeft.set(voltage);
    }
    public void setRightVoltage(double voltage) {
      m_motorFrontRight.set(voltage);
      m_motorBottomRight.set(voltage);
    }

    public double getLeftVoltage() {
      return m_motorFrontLeft.get();
    }

    public double getRightVoltage() {
      return m_motorFrontRight.get();
    }
    public void setLeftSpeed(double speed) {
      m_motorFrontLeft.set(speed);
      m_motorBottomLeft.set(speed);
    }
    public void setRightSpeed(double speed) {
      m_motorFrontRight.set(speed);
      m_motorBottomRight.set(speed);
    }

    public double getLeftSpeed() {
      return m_motorFrontLeft.get();
    }

    public double getRightSpeed() {
      return m_motorFrontRight.get();
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
