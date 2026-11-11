// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveDrivetrainForTime extends Command {
  private double m_targetTime;
  private Drivetrain m_Drivetrain;
  private Timer m_timer = new Timer();
  public MoveDrivetrainForTime(double targetTime, Drivetrain drivetrain) {
    m_targetTime = targetTime;
    m_Drivetrain = drivetrain;
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     m_timer.start();
     m_timer.reset();
  }
  
  @Override
  public void execute() {
    m_Drivetrain.setLeftSpeed(DrivetrainConstants.kMultiplier);
    m_Drivetrain.setRightSpeed(DrivetrainConstants.kMultiplier);
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
    return m_timer.get() >= m_targetTime;
  }
}
