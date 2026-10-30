// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDrivetrain extends Command {
  /** Creates a new ArcadeDrivetrain. */
  private Drivetrain m_drivetrain;
  private XboxController m_driveController;
  public ArcadeDrivetrain(Drivetrain drivetrain, XboxController driverController) {
    m_drivetrain = drivetrain;
    m_driveController = driverController;
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = -m_driveController.getRawAxis(IOConstants.kYAxis);
    double turn = m_driveController.getRawAxis(IOConstants.kXAxis);
    double leftDutyCycle = forward + turn;
    double rightDutyCycle = forward - turn;
    leftDutyCycle *= DrivetrainConstants.kSpeedMultiplier;
    rightDutyCycle *= DrivetrainConstants.kSpeedMultiplier;
    m_drivetrain.setLeftDutyCycle(leftDutyCycle);
    m_drivetrain.setRightDutyCycle(rightDutyCycle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftDutyCycle(0);
    m_drivetrain.setRightDutyCycle(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
