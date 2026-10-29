// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ArcadeDriveTrain;
import frc.robot.commands.LockWheels;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SwerveSteer;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);
  private XboxController m_XBoxController = new XboxController(IOConstants.port);
  private DriveTrain m_driveTrain = new DriveTrain();
  private ArcadeDriveTrain m_arcadeDriveTrain = new ArcadeDriveTrain(m_driveTrain, m_XBoxController);


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_driveTrain.setDefaultCommand(m_arcadeDriveTrain);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
