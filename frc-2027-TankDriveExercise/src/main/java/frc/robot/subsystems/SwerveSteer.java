// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

public class SwerveSteer extends SubsystemBase {
  private TalonFX m_frontLeftMotor = new TalonFX(DriveConstants.kFrontLeftTurnID);
  private TalonFX m_frontRightMotor = new TalonFX(DriveConstants.kFrontRightTurnID);
  private TalonFX m_backLeftMotor = new TalonFX(DriveConstants.kBackLeftTurnID);
  private TalonFX m_backRightMotor = new TalonFX(DriveConstants.kBackRightTurnID);

  public final DutyCycleEncoder m_frontLeftAbsoluteEncoder = new DutyCycleEncoder(DriveConstants.kFrontLeftAbsoluteEncoderPort, 2.0 * Math.PI, DriveConstants.kFrontLeftEncoderOffset);
  public final DutyCycleEncoder m_frontRightAbsoluteEncoder = new DutyCycleEncoder(DriveConstants.kFrontRightAbsoluteEncoderPort, 2.0 * Math.PI, DriveConstants.kFrontRightEncoderOffset);
  public final DutyCycleEncoder m_backLeftAbsoluteEncoder = new DutyCycleEncoder(DriveConstants.kBackLeftAbsoluteEncoderPort, 2.0 * Math.PI, DriveConstants.kBackLeftEncoderOffset);
  public final DutyCycleEncoder m_backRightAbsoluteEncoder = new DutyCycleEncoder(DriveConstants.kBackRightAbsoluteEncoderPort, 2.0 * Math.PI, DriveConstants.kBackRightEncoderOffset);

  private final DutyCycleOut m_turnMotorInput = new DutyCycleOut(0);

  private final PIDController m_turnPID[] = new PIDController[4];
  // new PIDController(DriveConstants.kPTurn, DriveConstants.kITurn, DriveConstants.kDTurn);

  /** Creates a new SwerveSteer. */
  public SwerveSteer() {
    for (int i = 0; i < 4; i++) {
      m_turnPID[i] = new PIDController(DriveConstants.kPTurn, DriveConstants.kITurn, DriveConstants.kDTurn);
      m_turnPID[i].setTolerance(0);
      m_turnPID[i].enableContinuousInput(-Math.PI, Math.PI);
    }
    // m_frontLeftMotor.getConfigurator().apply(new TalonFXConfiguration());
    // var frontLeftConfigs = new MotorOutputConfigs();
    // frontLeftConfigs.NeutralMode = NeutralModeValue.Brake;
    // frontLeftConfigs.Inverted = InvertedValue.Clockwise_Positive;
    // m_frontLeftMotor.getConfigurator().apply(frontLeftConfigs);

    // m_frontRightMotor.getConfigurator().apply(new TalonFXConfiguration());
    // var frontRightConfigs = new MotorOutputConfigs();
    // frontRightConfigs.NeutralMode = NeutralModeValue.Brake;
    // frontRightConfigs.Inverted = InvertedValue.Clockwise_Positive;
    // m_frontRightMotor.getConfigurator().apply(frontRightConfigs);

    // m_backLeftMotor.getConfigurator().apply(new TalonFXConfiguration());
    // var backLeftConfigs = new MotorOutputConfigs();
    // backLeftConfigs.NeutralMode = NeutralModeValue.Brake;
    // backLeftConfigs.Inverted = InvertedValue.Clockwise_Positive;
    // m_backLeftMotor.getConfigurator().apply(backLeftConfigs);

    // m_backRightMotor.getConfigurator().apply(new TalonFXConfiguration());
    // var backRightConfigs = new MotorOutputConfigs();
    // backRightConfigs.NeutralMode = NeutralModeValue.Brake;
    // backRightConfigs.Inverted = InvertedValue.Clockwise_Positive;
    // m_backRightMotor.getConfigurator().apply(backRightConfigs);

    // var turnCurrentConfigs = new CurrentLimitsConfigs();
    // turnCurrentConfigs.StatorCurrentLimit = 20;
    // turnCurrentConfigs.StatorCurrentLimitEnable = true;

    // m_frontLeftMotor.getConfigurator().apply(turnCurrentConfigs);
    // m_frontRightMotor.getConfigurator().apply(turnCurrentConfigs);
    // m_backLeftMotor.getConfigurator().apply(turnCurrentConfigs);
    // m_backRightMotor.getConfigurator().apply(turnCurrentConfigs);

    reconfigureMotor(m_frontLeftMotor);
    reconfigureMotor(m_frontRightMotor);
    reconfigureMotor(m_backLeftMotor);
    reconfigureMotor(m_frontRightMotor);
  }

  private void reconfigureMotor(TalonFX turnMotor) {
    turnMotor.getConfigurator().apply(new TalonFXConfiguration());
    var turnConfigs = new MotorOutputConfigs();
    turnConfigs.DutyCycleNeutralDeadband = 0.04;
    turnConfigs.NeutralMode = NeutralModeValue.Brake;
    turnConfigs.Inverted = InvertedValue.Clockwise_Positive;
    turnMotor.getConfigurator().apply(turnConfigs);

    var turnConfigurator = turnMotor.getConfigurator();
    var turnCurrentConfigs = new CurrentLimitsConfigs();
    turnCurrentConfigs.StatorCurrentLimit = 20;
    turnCurrentConfigs.StatorCurrentLimitEnable = true;
    turnConfigurator.apply(turnCurrentConfigs);
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

  public void resetWheelAngle(double setPointRads) {
    m_frontLeftMotor.setControl(m_turnMotorInput.withOutput(
      m_turnPID[0].calculate(MathUtil.angleModulus(m_frontLeftAbsoluteEncoder.get()), setPointRads)
    ));

    m_frontRightMotor.setControl(m_turnMotorInput.withOutput(
      m_turnPID[1].calculate(MathUtil.angleModulus(m_frontRightAbsoluteEncoder.get()), setPointRads)
    ));

    m_backLeftMotor.setControl(m_turnMotorInput.withOutput(
      m_turnPID[2].calculate(MathUtil.angleModulus(m_backLeftAbsoluteEncoder.get()), setPointRads)
    ));

    m_backRightMotor.setControl(m_turnMotorInput.withOutput(
      m_turnPID[3].calculate(MathUtil.angleModulus(m_backRightAbsoluteEncoder.get()), setPointRads)
    ));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
