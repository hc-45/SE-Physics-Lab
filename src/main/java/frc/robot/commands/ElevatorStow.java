package frc.robot.commands;

import frc.robot.subsystems.elevator.Elevator.ElevatorState;

public class ElevatorStow extends ElevatorSetState {
    public ElevatorStow() {
        super(ElevatorState.STOW);
    }
}