// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ArcadeDrivetrain;
import frc.robot.commands.LockWheels;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.SwerveSteer;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);
  private XboxController m_driverController = new XboxController(DrivetrainConstants.kDriverControllerPort);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrivetrain m_arcadeDrivetrain = new ArcadeDrivetrain(m_drivetrain, m_driverController);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_drivetrain.setDefaultCommand(m_arcadeDrivetrain);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
