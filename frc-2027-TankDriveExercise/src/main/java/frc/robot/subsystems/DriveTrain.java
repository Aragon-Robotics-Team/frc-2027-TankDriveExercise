// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import javax.security.auth.login.LoginException;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;  

public class DriveTrain extends SubsystemBase {
   private TalonFX m_frontLeft = new TalonFX(DriveConstants.kFrontLeftMotorID);
   private TalonFX m_frontRight = new TalonFX(DriveConstants.kFrontRightMotorID);
   private TalonFX m_backLeft = new TalonFX(DriveConstants.kBackLeftMotorID);
   private TalonFX m_backRight = new TalonFX(DriveConstants.kBackRightMotorID);

   private TalonFXConfiguration m_frontLeftConfiguration = new TalonFXConfiguration();
   private TalonFXConfiguration m_frontRightConfiguration = new TalonFXConfiguration();
   private TalonFXConfiguration m_backRightConfiguration = new TalonFXConfiguration();
   private TalonFXConfiguration m_backLeftConfiguration = new TalonFXConfiguration();
  /** Creates a new DriveTrain. */
  public DriveTrain() {
   m_backLeftConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
   m_backRightConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
   m_frontRightConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
   m_frontLeftConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
  
  m_backLeftConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_backRightConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_frontLeftConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  m_frontRightConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

  m_frontLeft.getConfigurator().apply(m_frontLeftConfiguration);
  m_backLeft.getConfigurator().apply(m_backLeftConfiguration);
  m_frontRight.getConfigurator().apply(m_frontRightConfiguration);
  m_backRight.getConfigurator().apply(m_backRightConfiguration);
  }

  @Override
  public void periodic() {
    Logger.recordOutput("backLeftMotorSpeed", getBackLeft());
    Logger.recordOutput("frontLeftMotorSpeed", getFrontLeft());
    Logger.recordOutput("backRightMotorSpeed", getBackRight());
    Logger.recordOutput("frontRightMotorSpeed", getFrontRight());

    // This method will be called once per scheduler run
  }

  public void setLeftSpeed(double dutyCycle){
    m_frontLeft.set(dutyCycle);
    m_backLeft.set(dutyCycle);
  }

    public void setRightSpeed(double dutyCycle){
    m_backRight.set(dutyCycle);
    m_frontRight.set(dutyCycle);
  }

  public double getBackLeft(){
    return m_backLeft.get();
  }
  
  public double getFrontLeft(){
    return m_frontLeft.get();
  }

  public double getBackRight(){
    return m_backRight.get();
  }

  public double getFrontRight(){
    return m_frontRight.get();
  }

}
