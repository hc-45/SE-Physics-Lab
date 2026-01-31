package frc.robot.subsystems.flywheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Settings;

public class Flywheel extends SubsystemBase {
    private static Flywheel instance;

    static {
        if (!Robot.isReal()) {
         instance = new FlywheelSimu();
        }
    }


    
    public static Flywheel getInstance() {
        return instance;
    }

    public enum FlywheelState {
        STOP(Settings.Elevator.STOW_HEIGHT_METERS),
        SHOOT(Settings.Elevator.L1_HEIGHT_METERS),
        FERRY(Settings.Elevator.L2_HEIGHT_METERS),
        UNJAM(Settings.Elevator.L3_HEIGHT_METERS);

        
        private double targetRPM;

        private FlywheelState(double targetHeight) {
            this.targetRPM = targetHeight;
        }

        public double getTargetRPM() {
            return this.targetRPM;
        }
    }

    private FlywheelState state;

    public FlywheelState getState() {
        return this.state;
    }

    public void setState(FlywheelState state) {
        this.state = state;
    }
}
