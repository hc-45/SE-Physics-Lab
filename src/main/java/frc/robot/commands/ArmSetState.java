package frc.robot.commands;

import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Arm.ArmState;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ArmSetState extends InstantCommand {
    private final Arm arm;
    private ArmState armState;
    
    public ArmSetState(ArmState armState) {
        this.arm = Arm.getInstance();
        this.armState = armState;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setState(armState);
    }
}