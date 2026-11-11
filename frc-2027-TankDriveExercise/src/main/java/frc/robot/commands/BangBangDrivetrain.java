// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BangBangDrivetrain extends Command {

  private double m_distanceToMove;
  private Drivetrain m_drivetrain = new Drivetrain();


  /** Creates a new BangBangDrivetrain. */
  public BangBangDrivetrain(double distanceToMove, Drivetrain drivetrain) {
    m_drivetrain = drivetrain; 
    m_distanceToMove = distanceToMove;
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     
    m_distanceToMove += m_drivetrain.getBackLeftStatusSignal();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.setLeftDutyCycle(0.2);
    m_drivetrain.setRightDutyCycle(0.2);
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

    return m_distanceToMove <= m_drivetrain.getBackLeftDutyCycle();
  
  }
}
