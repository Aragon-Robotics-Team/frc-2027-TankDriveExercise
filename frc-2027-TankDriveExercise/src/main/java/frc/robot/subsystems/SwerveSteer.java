// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

public class SwerveSteer extends SubsystemBase {
  private TalonFX m_frontLeftMotor = new TalonFX(DriveConstants.kFrontLeftTurnID);
  private TalonFX m_frontRightMotor = new TalonFX(DriveConstants.kFrontRightTurnID);
  private TalonFX m_backLeftMotor = new TalonFX(DriveConstants.kBackLeftTurnID);
  private TalonFX m_backRightMotor = new TalonFX(DriveConstants.kBackRightTurnID);

  private final DutyCycleOut m_turnMotorInput = new DutyCycleOut(0);

  /** Creates a new SwerveSteer. */
  public SwerveSteer() {
    m_frontLeftMotor.getConfigurator().apply(new TalonFXConfiguration());
    var frontLeftConfigs = new MotorOutputConfigs();
    frontLeftConfigs.NeutralMode = NeutralModeValue.Brake;
    frontLeftConfigs.Inverted = InvertedValue.Clockwise_Positive;
    m_frontLeftMotor.getConfigurator().apply(frontLeftConfigs);

    m_frontRightMotor.getConfigurator().apply(new TalonFXConfiguration());
    var frontRightConfigs = new MotorOutputConfigs();
    frontRightConfigs.NeutralMode = NeutralModeValue.Brake;
    frontRightConfigs.Inverted = InvertedValue.Clockwise_Positive;
    m_frontRightMotor.getConfigurator().apply(frontRightConfigs);

    m_backLeftMotor.getConfigurator().apply(new TalonFXConfiguration());
    var backLeftConfigs = new MotorOutputConfigs();
    backLeftConfigs.NeutralMode = NeutralModeValue.Brake;
    backLeftConfigs.Inverted = InvertedValue.Clockwise_Positive;
    m_backLeftMotor.getConfigurator().apply(backLeftConfigs);

    m_backRightMotor.getConfigurator().apply(new TalonFXConfiguration());
    var backRightConfigs = new MotorOutputConfigs();
    backRightConfigs.NeutralMode = NeutralModeValue.Brake;
    backRightConfigs.Inverted = InvertedValue.Clockwise_Positive;
    m_backRightMotor.getConfigurator().apply(backRightConfigs);
  }

  public void setZeroSpeed() {
    m_frontLeftMotor.setControl(m_turnMotorInput.withOutput(0));
    m_frontRightMotor.setControl(m_turnMotorInput.withOutput(0));
    m_backLeftMotor.setControl(m_turnMotorInput.withOutput(0));
    m_backRightMotor.setControl(m_turnMotorInput.withOutput(0));
  }

  public double getSpeed() {
    return m_frontLeftMotor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
