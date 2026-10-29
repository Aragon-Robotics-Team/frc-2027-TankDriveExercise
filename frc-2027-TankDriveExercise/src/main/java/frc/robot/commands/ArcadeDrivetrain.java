// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDrivetrain extends Command {

  private Drivetrain m_drivetrain;

  private XboxController m_xboxController;


  /** Creates a new ArcadeDrivetrain. */
  public ArcadeDrivetrain(Drivetrain drivetrain, XboxController controller) {
    m_drivetrain = drivetrain;      
    m_xboxController = controller;
    addRequirements(m_drivetrain);
 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnSpeed = m_xboxController.getRawAxis(IOConstants.kAxisIDX);
    turnSpeed *= DrivetrainConstants.kArcadeDrivetrainMult;
    double forwardSpeed = -m_xboxController.getRawAxis(IOConstants.kAxisIDY);
    forwardSpeed *= DrivetrainConstants.kArcadeDrivetrainMult;

    double leftWheelsSpeed = forwardSpeed + turnSpeed;
    double rightWheelsSpeed = forwardSpeed - turnSpeed;

    m_drivetrain.setLeftDutyCycle(leftWheelsSpeed);
    m_drivetrain.setRightDutyCycle(rightWheelsSpeed);
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
