package frc.robot.subsystems.arm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.Arm.ArmState;

public class ArmVisualizer {
    public static ArmVisualizer instance;

    static {
        instance = new ArmVisualizer();
    }

    public static ArmVisualizer getInstance() {
        return instance;
    }

    private final Mechanism2d canvas;
    private double width, height;

    private final MechanismRoot2d ArmPivot;
    private final MechanismLigament2d Arm;

    private ArmVisualizer() {
        width = Units.inchesToMeters(20);
        height = Units.inchesToMeters(20);

        canvas = new Mechanism2d(width, height);

        ArmPivot = canvas.getRoot("Arm Pivot", width/2, height/2);

        Arm = new MechanismLigament2d(
            "Arm",
            Units.inchesToMeters(5),
            ArmState.ONE.getTargetAngle().getDegrees(),
            4,
            new Color8Bit(Color.kBlueViolet)
        );

        ArmPivot.append(Arm);
    }

    public void updateArmAngle(Rotation2d ArmAngle) {
        ArmPivot.setPosition(width/2, height/2);
        Arm.setAngle(ArmAngle);

        Arm.setColor(new Color8Bit(Color.kGreen));

        SmartDashboard.putData("Visualizers/Arm", canvas);
    }
}
