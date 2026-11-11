// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead cd https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDrivetrain extends Command  {
  private XboxController m_xBoxController;
  private Drivetrain m_Drivetrain;
  private double m_speedright;
  private double m_speedleft;

  public ArcadeDrivetrain(XboxController controller, Drivetrain drivetrain) {
    m_xBoxController = controller;
    m_Drivetrain = drivetrain;
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnSpeed = m_xBoxController.getRawAxis(IOConstants.kXAxisID);
    double forwardSpeed = -m_xBoxController.getRawAxis(IOConstants.kYAxisID);
    m_speedleft = forwardSpeed + turnSpeed;
    m_speedleft = m_speedleft * DrivetrainConstants.kMultiplier;
    m_speedright = forwardSpeed - turnSpeed;
    m_speedright = m_speedright * DrivetrainConstants.kMultiplier;
    m_Drivetrain.setLeftSpeed(m_speedleft);
    m_Drivetrain.setRightSpeed(m_speedright);
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
    return false;
  }
}
