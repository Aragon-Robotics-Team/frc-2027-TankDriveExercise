// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDriveTrain extends Command {

  private DriveTrain m_drivetrain;
  private XboxController m_Controller;
  /** Creates a new ArcadeDriveTrain. */

  public ArcadeDriveTrain(DriveTrain drivetrain, XboxController controller) {

    m_drivetrain = drivetrain;
    m_Controller = controller;
    
    addRequirements(m_drivetrain);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.

  @Override
  public void execute() {

    double front = -m_Controller.getRawAxis(IOConstants.kYAxis);
    double back = m_Controller.getRawAxis(IOConstants.kXAxis);
    double left = front + back;
    double right = front - back;
    

    left *= IOConstants.kSpeedMultiplier;
    right *= IOConstants.kSpeedMultiplier;
    
    m_drivetrain.setLeftSpeed(left);
    m_drivetrain.setRightSpeed(-right);


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
