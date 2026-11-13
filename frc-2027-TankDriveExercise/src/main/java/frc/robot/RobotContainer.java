// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrivetrain;
import frc.robot.commands.BangBangDrivetrain;
import frc.robot.commands.LockWheels;
import frc.robot.commands.MoveForTime;
import frc.robot.commands.MoveWithPID;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.SwerveSteer;
import frc.robot.subsystems.TuningModule;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);
  private XboxController m_driverController = new XboxController(IOConstants.kDriverControllerPort);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrivetrain m_arcadeDrivetrain = new ArcadeDrivetrain(m_drivetrain, m_driverController);
  private MoveForTime m_moveForTime = new MoveForTime(DrivetrainConstants.kFinalTime, m_drivetrain);
  private JoystickButton m_driveForTimeButton = new JoystickButton(m_driverController, IOConstants.kDriveForTimeButton);
  private JoystickButton m_bangBangButton = new JoystickButton(m_driverController, IOConstants.kBangBangButton);
  private BangBangDrivetrain m_bangBangDrivetrain = new BangBangDrivetrain(m_drivetrain, DrivetrainConstants.kTargetDistance); 
  private JoystickButton m_moveWithPIDButton = new JoystickButton(m_driverController, IOConstants.kMoveWithPIDButton);
  private MoveWithPID m_moveWithPID = new MoveWithPID(m_drivetrain, DrivetrainConstants.kTargetDistance);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_drivetrain.setDefaultCommand(m_arcadeDrivetrain);
    m_driveForTimeButton.whileTrue(m_moveForTime);
    m_bangBangButton.whileTrue(m_bangBangDrivetrain);
    m_moveWithPIDButton.whileTrue(m_moveWithPID);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
