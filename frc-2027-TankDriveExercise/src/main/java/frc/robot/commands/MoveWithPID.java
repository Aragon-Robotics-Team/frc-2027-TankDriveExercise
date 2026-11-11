// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.TuningModule;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveWithPID extends Command {
  private Drivetrain m_drivetrain = new Drivetrain();
  private PIDController m_pidController = new PIDController(DrivetrainConstants.kP, DrivetrainConstants.kI, DrivetrainConstants.kD);
  private TuningModule m_tuningModule = new TuningModule("drivePID",  m_pidController);
  private double m_initialPosition;
  private double m_targetPosition;
  private double m_targetDistance;
  public MoveWithPID(Drivetrain drivetrain, double targetDistance) {
    m_drivetrain = drivetrain;
    m_targetDistance = targetDistance;
    addRequirements(m_drivetrain);
  }
  
  @Override
  public void initialize() {
    m_initialPosition = m_drivetrain.getBottomLeftPosition();
    m_targetPosition = m_initialPosition + m_targetDistance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {;
    m_drivetrain.setLeftSpeed(m_pidController.calculate(m_drivetrain.getBottomLeftPosition(), m_targetPosition));
    m_drivetrain.setRightSpeed(m_pidController.calculate(m_drivetrain.getBottomLeftPosition(), m_targetPosition));
    Logger.recordOutput("currentPosition", m_drivetrain.getBottomLeftPosition());
    Logger.recordOutput("targetPosition", m_targetPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
