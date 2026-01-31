package frc.robot.subsystems.flywheel;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class FlywheelSimu extends Flywheel {
    private final FlywheelSim sim;

    LinearSystem<N1, N1, N1> motorSim;
    private PIDController flywheelPID;
    private SimpleMotorFeedforward flywheelFF;

    public FlywheelSimu() {
        this.sim = new FlywheelSim(
            LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX44(1), 1, 1), 
            new DCMotor(12, 6, 40, 12, 20, 1), 
            null);

        flywheelFF = new SimpleMotorFeedforward(0,0,0);
        flywheelPID = new PIDController(0,0,0);
    }


    @Override
    public void periodic() {
        double pidvoltage = flywheelPID.calculate(sim.getAngularVelocityRadPerSec(), getState().getTargetRPM());
        double ffvoltage = flywheelFF.calculate(sim.getAngularVelocityRadPerSec());
        sim.setInputVoltage(pidvoltage + ffvoltage);


    }
}
