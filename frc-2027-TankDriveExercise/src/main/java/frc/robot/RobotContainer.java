// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrivetrain;
import frc.robot.commands.BangBangDrivetrain;
import frc.robot.commands.LockWheels;
import frc.robot.commands.MoveDrivetrainForTime;
import frc.robot.commands.MoveWithPID;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.SwerveSteer;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);
  private XboxController m_xBoxController = new XboxController(0);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrivetrain m_arcadeDrivetrain = new ArcadeDrivetrain(m_xBoxController, m_drivetrain);
  private MoveDrivetrainForTime m_moveDrivetrainForTime = new MoveDrivetrainForTime(DrivetrainConstants.kTargetTime, m_drivetrain);
  private BangBangDrivetrain m_bangBangDrivetrain = new BangBangDrivetrain(m_drivetrain, DrivetrainConstants.kTargetDistance);
  private JoystickButton m_moveWithPIDButton = new JoystickButton(m_xBoxController, IOConstants.kButtonID);

  private MoveWithPID m_moveWithPID = new MoveWithPID(m_drivetrain, DrivetrainConstants.kTargetDistance);

  public RobotContainer() {
    configureBindings();
  }

  public void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_drivetrain.setDefaultCommand(m_arcadeDrivetrain);
    m_moveWithPIDButton.whileTrue(m_moveWithPID);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
