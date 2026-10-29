// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import org.littletonrobotics.junction.Logger;


import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  private TalonFX m_frontLeftMotor = new TalonFX(DrivetrainConstants.kFrontLeftMotorID);
  private TalonFX m_frontRightMotor = new TalonFX(DrivetrainConstants.kFrontRightMotorID);

  private TalonFX m_backLeftMotor = new TalonFX(DrivetrainConstants.kBackLeftMotorID);
  private TalonFX m_backRightMotor = new TalonFX(DrivetrainConstants.kBackRightMotorID);

  private StatusSignal<Voltage> m_frontLeftVoltage = m_frontLeftMotor.getMotorVoltage();
  private StatusSignal<Voltage> m_backLeftVoltage = m_frontLeftMotor.getMotorVoltage();
  private StatusSignal<Voltage> m_frontRightVoltage = m_frontLeftMotor.getMotorVoltage();
  private StatusSignal<Voltage> m_backRightVoltage = m_frontLeftMotor.getMotorVoltage();

  private TalonFXConfiguration m_frontLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_frontRightMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backLeftMotorConfig = new TalonFXConfiguration();
  private TalonFXConfiguration m_backRightMotorConfig = new TalonFXConfiguration();
  

  public Drivetrain() {

    m_backLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_backRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_frontLeftMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_frontRightMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    m_backLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    m_backRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    m_frontLeftMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    m_frontRightMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    m_backLeftMotor.getConfigurator().apply(m_backLeftMotorConfig);
    m_backRightMotor.getConfigurator().apply(m_frontRightMotorConfig);
    m_frontLeftMotor.getConfigurator().apply(m_frontLeftMotorConfig);
    m_frontRightMotor.getConfigurator().apply(m_frontRightMotorConfig);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("backLeftMotorSpeed", getBackLeftDutyCycle());
    Logger.recordOutput("frontLeftMotorSpeed", getFrontLeftDutyCycle());
    Logger.recordOutput("backRightMotorSpeed", getBackRightDutyCycle());
    Logger.recordOutput("frontRightMotorSpeed", getFrontRightDutyCycle());

  }

  
  public void setLeftVoltage(double leftVoltage) {
    m_frontLeftMotor.setVoltage(leftVoltage);
    m_backLeftMotor.setVoltage(leftVoltage);
  }

  public void setRightVoltage(double rightVoltage) {
    m_backRightMotor.setVoltage(rightVoltage);
    m_frontRightMotor.setVoltage(rightVoltage);
  }

  public double getFrontLeftVoltage() {
    return m_frontLeftVoltage.refresh().getValueAsDouble();
  }

  public double getBackLeftVoltage() {
    return m_backLeftVoltage.refresh().getValueAsDouble();
  }

  public double getFrontRightVoltage() {
    return m_frontRightVoltage.refresh().getValueAsDouble();
  }

  public double getBackRightVoltage() {
    return m_backRightVoltage.refresh().getValueAsDouble();
  }

  public void setLeftDutyCycle(double leftDutyCycle) {
    m_backLeftMotor.set(leftDutyCycle);
    m_frontLeftMotor.set(leftDutyCycle);
  }

  public void setRightDutyCycle(double rightDutyCycle) {
    m_backRightMotor.set(rightDutyCycle);
    m_frontRightMotor.set(rightDutyCycle);
  }

  public double getBackRightDutyCycle() {
    return m_backRightMotor.get();
  }

  public double getFrontRightDutyCycle() {
    return m_frontRightMotor.get();
  }

  public double getBackLeftDutyCycle() {
    return m_backLeftMotor.get();
  }

  public double getFrontLeftDutyCycle() {
    return m_frontLeftMotor.get();
  }

}
