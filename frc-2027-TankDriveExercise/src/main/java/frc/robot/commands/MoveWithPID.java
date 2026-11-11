// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TuningModule;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveWithPID extends Command {
  private DriveTrain m_drivetrain;
  private double m_speed;
  private double m_targetPosition;
  private PIDController m_PIDController = new PIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD);
  private TuningModule m_tuningModule = new TuningModule("drivetrainPIDTuning", m_PIDController);

  /** Creates a new MoveWithPID. */
  public MoveWithPID(DriveTrain drivetrain) {
    m_drivetrain = drivetrain;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_targetPosition = m_drivetrain.getDistanceTraveledFrontLeft() + DriveConstants.kTargetDistance;
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_speed = m_PIDController.calculate(m_drivetrain.getDistanceTraveledFrontLeft(), m_targetPosition);
    m_drivetrain.setLeftSpeed(m_speed);
    m_drivetrain.setRightSpeed(-m_speed);
    
    Logger.recordOutput("currentPosition", m_drivetrain.getDistanceTraveledFrontLeft());
    Logger.recordOutput("targetPosition", m_targetPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
