// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BangBangDrivetrain extends Command {
  private DriveTrain m_drivetrain;
  private double m_targetDistance;

  
  /** Creates a new BangBangDrivetrain. */
  public BangBangDrivetrain(DriveTrain drivetrain, double distance) {
    m_drivetrain = drivetrain;
    m_targetDistance = distance;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
       m_targetDistance += m_drivetrain.getDistanceTraveledFrontLeft();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.setLeftSpeed(0.2);
    m_drivetrain.setRightSpeed(-0.2);
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
    return m_targetDistance <= m_drivetrain.getDistanceTraveledFrontLeft();
  }
}
