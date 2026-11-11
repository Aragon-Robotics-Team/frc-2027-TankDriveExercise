// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TuningModule extends SubsystemBase {
  /** Creates a new tuningSlider. */

  private NetworkTableInstance m_tableInstance = NetworkTableInstance.getDefault();
  private NetworkTable m_table = m_tableInstance.getTable("Tuning");
  private HashMap<DoubleEntry, List<Consumer<DoubleEntry>>> m_constants = new HashMap<DoubleEntry, List<Consumer<DoubleEntry>>>();

  public TuningModule(String name, PIDController pidController) {
    instantiatePID(name, pidController);
  }

  public TuningModule(String name, SimpleMotorFeedforward motorFeedforward) {
    instantiateSimpleMotorFeedforward(name, motorFeedforward);
  }

  public TuningModule(String name, ArmFeedforward armFeedforward) {
    instantiateArmFeedforward(name, armFeedforward);
  }

  public TuningModule(String name, ElevatorFeedforward elevatorFeedforward) {
    instantiateElevatorFeedforward(name, elevatorFeedforward);
  }

  public TuningModule(String name, PIDController pidController, SimpleMotorFeedforward motorFeedforward) {
    instantiatePID(name, pidController);
    instantiateSimpleMotorFeedforward(name, motorFeedforward);
  }

  public TuningModule(String name, PIDController pidController, ArmFeedforward armFeedforward) {
    instantiatePID(name, pidController);
    instantiateArmFeedforward(name, armFeedforward);
  }

  public TuningModule(String name, PIDController pidController, ElevatorFeedforward elevatorFeedforward) {
    instantiatePID(name, pidController);
    instantiateElevatorFeedforward(name, elevatorFeedforward);
  }

  private void instantiatePID(String name, PIDController pidController) {
    m_constants.put(m_table.getDoubleTopic(name + "/pid/kP").getEntry(pidController.getP()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> pidController.setP(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/pid/kI").getEntry(pidController.getI()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> pidController.setI(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/pid/kD").getEntry(pidController.getD()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> pidController.setD(entry.get())));
  }

  private void instantiateSimpleMotorFeedforward(String name, SimpleMotorFeedforward feedforward) {
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kS").getEntry(feedforward.getKs()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKs(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kV").getEntry(feedforward.getKv()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKv(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kA").getEntry(feedforward.getKa()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKa(entry.get())));
  }

  private void instantiateArmFeedforward(String name, ArmFeedforward feedforward) {
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kS").getEntry(feedforward.getKs()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKs(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kG").getEntry(feedforward.getKg()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKg(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kV").getEntry(feedforward.getKv()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKv(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kA").getEntry(feedforward.getKa()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKa(entry.get())));
  }

  private void instantiateElevatorFeedforward(String name, ElevatorFeedforward feedforward) {
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kS").getEntry(feedforward.getKs()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKs(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kG").getEntry(feedforward.getKg()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKg(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kV").getEntry(feedforward.getKv()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKv(entry.get())));
    m_constants.put(m_table.getDoubleTopic(name + "/ff/kA").getEntry(feedforward.getKa()), List.of((DoubleEntry entry) -> entry.set(entry.get()), (DoubleEntry entry) -> feedforward.setKa(entry.get())));
  }

  @Override
  public void periodic() {
    for (DoubleEntry entry : m_constants.keySet()) {
      for (Consumer<DoubleEntry> c : m_constants.get(entry)) {
        c.accept(entry);
      }
    }
    // This method will be called once per scheduler run
  }
}
