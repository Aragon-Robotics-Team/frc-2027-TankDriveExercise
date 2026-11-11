// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrivetrain;
import frc.robot.commands.BangBangDrivetrain;
import frc.robot.commands.LockWheels;
import frc.robot.commands.MoveDrivetrainforTime;
import frc.robot.commands.MoveWithPID;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.SwerveSteer;
import frc.robot.subsystems.TuningModule;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);

  private XboxController m_xboxController = new XboxController(IOConstants.kPortID);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrivetrain m_arcadeDrivetrain = new ArcadeDrivetrain(m_drivetrain, m_xboxController);
  private JoystickButton m_joystickButtonTime = new JoystickButton(m_xboxController, IOConstants.kJoystickAButtonID);
  private JoystickButton m_joystickButtonBangBang = new JoystickButton(m_xboxController, IOConstants.kJoystickBButtonID);
  private JoystickButton m_joystickButtonMoveWithPID = new JoystickButton(m_xboxController, IOConstants.kJoystickXButtonID);
  private MoveWithPID m_moveWithPID = new MoveWithPID(m_drivetrain, DrivetrainConstants.kTargetDistance);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_drivetrain.setDefaultCommand(m_arcadeDrivetrain);
    m_joystickButtonTime.whileTrue(new MoveDrivetrainforTime(m_drivetrain, 2));
    m_joystickButtonBangBang.whileTrue(new BangBangDrivetrain(2, m_drivetrain));
    m_joystickButtonMoveWithPID.whileTrue(m_moveWithPID);
    

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
