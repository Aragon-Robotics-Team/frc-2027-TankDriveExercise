// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDriveTrain;
import frc.robot.commands.BangBangDrivetrain;
import frc.robot.commands.LockWheels;
import frc.robot.commands.MoveDrivetrainForTime;
import frc.robot.commands.MoveWithPID;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SwerveSteer;

public class RobotContainer {
  private SwerveSteer m_swerveSteer = new SwerveSteer();
  private LockWheels m_lockWheels = new LockWheels(m_swerveSteer);
  private XboxController m_XBoxController = new XboxController(IOConstants.port);
  private DriveTrain m_driveTrain = new DriveTrain();

  private ArcadeDriveTrain m_arcadeDriveTrain = new ArcadeDriveTrain(m_driveTrain, m_XBoxController);
  private JoystickButton m_buttonMoveForTime = new JoystickButton(m_XBoxController, IOConstants.kMoveForTimeButtonID);
  private MoveDrivetrainForTime m_drivetrainForTime = new MoveDrivetrainForTime(m_driveTrain, DriveConstants.kTargetTime);


  private JoystickButton m_buttonBangBang = new JoystickButton(m_XBoxController, IOConstants.kBangBangButtonID);
  private BangBangDrivetrain m_bangBangDrivetrain = new BangBangDrivetrain(m_driveTrain, DriveConstants.kTargetDistance);

  private JoystickButton m_buttonMoveWithPID = new JoystickButton(m_XBoxController, IOConstants.kMoveWithPIDButtonID);
  private MoveWithPID m_moveWithPID = new MoveWithPID(m_driveTrain);



  //what to put in second of BangBang...
  public RobotContainer() {
    configureBindings();



  }

  private void configureBindings() {
    m_swerveSteer.setDefaultCommand(m_lockWheels);
    m_driveTrain.setDefaultCommand(m_arcadeDriveTrain);
    m_buttonMoveForTime.whileTrue(m_drivetrainForTime);
    m_buttonBangBang.whileTrue(m_bangBangDrivetrain);
    m_buttonMoveWithPID.whileTrue(m_moveWithPID);
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
