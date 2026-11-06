// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private TalonFX m_frontLeftMotor = new TalonFX(DrivetrainConstants.kFrontLeftMotorID);
  private TalonFX m_frontRightMotor = new TalonFX(DrivetrainConstants.kFrontRightMotorID);
  private TalonFX m_backLeftMotor = new TalonFX(DrivetrainConstants.kBackLeftMotorID);
  private TalonFX m_backRightMotor = new TalonFX(DrivetrainConstants.kBackRightMotorID);
  private TalonFXConfiguration m_frontLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_frontRightMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backRightMotorConfig = new TalonFXConfiguration();
  private StatusSignal<Angle> m_frontLeftStatusSignal = m_frontLeftMotor.getPosition();
  private StatusSignal<Angle> m_frontRightStatusSignal = m_frontRightMotor.getPosition();
  private StatusSignal<Angle> m_backLeftStatusSignal = m_backLeftMotor.getPosition();
  private StatusSignal<Angle> m_backRightStatusSignal = m_backRightMotor.getPosition();

  public Drivetrain() {
  m_frontLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_frontRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  m_backLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_backRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
  m_frontLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_frontRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
  m_frontLeftMotorConfig.Feedback.SensorToMechanismRatio = (5.90026);
  m_frontRightMotorConfig.Feedback.SensorToMechanismRatio = (6.7480);
  m_backLeftMotorConfig.Feedback.SensorToMechanismRatio = (6.7460);
  m_backRightMotorConfig.Feedback.SensorToMechanismRatio = (6.7460);
  m_frontLeftMotor.getConfigurator().apply(m_frontLeftMotorConfig);
  m_frontRightMotor.getConfigurator().apply(m_frontRightMotorConfig);
  m_backLeftMotor.getConfigurator().apply(m_backLeftMotorConfig);
  m_backRightMotor.getConfigurator().apply(m_backRightMotorConfig);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("Drivetrain/FrontLeftDutyCycle", m_frontLeftMotor.get());
    Logger.recordOutput("Drivetrain/FrontRightDutyCycle", m_frontRightMotor.get());
    Logger.recordOutput("Drivetrain/BackLeftDutyCycle", m_backLeftMotor.get());
    Logger.recordOutput("Drivetrain/BackRightDutyCycle", m_backRightMotor.get());
    Logger.recordOutput("Drivetrain/DistanceTraveled", getFrontRightDistanceTraveled());
  }

  public void setLeftDutyCycle(double dutyCycle) {
    m_frontLeftMotor.set(dutyCycle);
    m_backLeftMotor.set(dutyCycle);
  }

  public double getFrontLeftDutyCycle() {
    return m_frontLeftMotor.get();
  }

  public double getBackLeftDutyCycle() {
    return m_backLeftMotor.get();
  }

  public void setRightDutyCycle(double dutyCycle) {
    m_frontRightMotor.set(dutyCycle);
    m_backRightMotor.set(dutyCycle);
  }

  public double getFrontRightDutyCycle() {
    return m_frontRightMotor.get();
  }

  public double getBackRightDutyCycle() {
    return m_backRightMotor.get();
  }

  public double getFrontLeftDistanceTraveled() {
    return m_frontLeftStatusSignal.refresh().getValueAsDouble() * 2 * DrivetrainConstants.kWheelRadius * Math.PI;
  }

    public double getFrontRightDistanceTraveled() {
    return m_frontRightStatusSignal.refresh().getValueAsDouble() * 2 * DrivetrainConstants.kWheelRadius * Math.PI;
  }

    public double getBackLeftDistanceTraveled() {
    return m_backLeftStatusSignal.refresh().getValueAsDouble() * 2 * DrivetrainConstants.kWheelRadius * Math.PI;
  }

    public double getBackRightDistanceTraveled() {
    return m_backRightStatusSignal.refresh().getValueAsDouble() * 2 * DrivetrainConstants.kWheelRadius * Math.PI;
  }
}
