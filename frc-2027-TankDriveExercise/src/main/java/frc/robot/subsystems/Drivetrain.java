// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private TalonFX m_frontLeftMotorID = new TalonFX(DrivetrainConstants.kFrontLeftMotorID);
  private TalonFX m_frontRightMotorID = new TalonFX(DrivetrainConstants.kFrontRightMotorID);
  private TalonFX m_backLeftMotorID = new TalonFX(DrivetrainConstants.kBackLeftMotorID);
  private TalonFX m_backRightMotorID = new TalonFX(DrivetrainConstants.kBackRightMotorID);
  private TalonFXConfiguration m_frontLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_frontRightMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backRightMotorConfig = new TalonFXConfiguration();
  
  public Drivetrain() {
  m_frontLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_frontRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_backRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
  m_frontLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_frontRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
  m_frontLeftMotorID.getConfigurator().apply(m_frontLeftMotorConfig);
  m_frontRightMotorID.getConfigurator().apply(m_frontRightMotorConfig);
  m_backLeftMotorID.getConfigurator().apply(m_backLeftMotorConfig);
  m_backRightMotorID.getConfigurator().apply(m_backRightMotorConfig);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("Drivetrain/FrontLeftDutyCycle", m_frontLeftMotorID.get());
    Logger.recordOutput("Drivetrain/FrontRightDutyCycle", m_frontRightMotorID.get());
    Logger.recordOutput("Drivetrain/BackLeftDutyCycle", m_backLeftMotorID.get());
    Logger.recordOutput("Drivetrain/BackRightDutyCycle", m_backRightMotorID.get());
  }

  public void setLeftDutyCycle(double dutyCycle) {
    m_frontLeftMotorID.set(dutyCycle);
    m_backLeftMotorID.set(dutyCycle);
  }

  public double getFrontLeftDutyCycle() {
    return m_frontLeftMotorID.get();
  }

  public double getBackLeftDutyCycle() {
    return m_backLeftMotorID.get();
  }

  public void setRightDutyCycle(double dutyCycle) {
    m_frontRightMotorID.setVoltage(dutyCycle);
    m_backRightMotorID.setVoltage(dutyCycle);
  }

  public double getFrontRightDutyCycle() {
    return m_frontRightMotorID.get();
  }

  public double getBackRightDutyCycle() {
    return m_backRightMotorID.get();
  }
}
