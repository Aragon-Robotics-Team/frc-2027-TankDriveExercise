// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BangBangDrivetrain extends Command {
  private Drivetrain m_Drivetrain;
  private double m_initialPosition;
  private double m_targetPosition;
  private double m_targetDistance;
  public BangBangDrivetrain(Drivetrain drivetrain, double targetDistance) {
    m_Drivetrain = drivetrain;
    m_targetDistance = targetDistance;
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialPosition = m_Drivetrain.getBottomLeftPosition();
    m_targetPosition = m_initialPosition + m_targetDistance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Drivetrain.setLeftSpeed(0.2);
    m_Drivetrain.setRightSpeed(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_Drivetrain.setLeftSpeed(0);
   m_Drivetrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_targetPosition <= m_Drivetrain.getBottomLeftPosition());
  }
}
