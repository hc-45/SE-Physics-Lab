package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.constants.Constants.OIConstants;
import frc.robot.constants.Constants.SwerveConstants;
import frc.robot.subsystems.SwerveDrive;

public class TeleopDrive extends Command {
    private final SwerveDrive swerveDrive;
    private final CommandXboxController controller;
    
    public TeleopDrive(SwerveDrive swerveDrive, CommandXboxController controller) {
        this.swerveDrive = swerveDrive;
        this.controller = controller;
        
        addRequirements(swerveDrive);
    }
    
    @Override
    public void execute() {
        double xInput = -controller.getRawAxis(OIConstants.LEFT_Y_AXIS);
        double yInput = -controller.getRawAxis(OIConstants.LEFT_X_AXIS);
        double rotInput = -controller.getRawAxis(OIConstants.RIGHT_X_AXIS);
        
        xInput = MathUtil.applyDeadband(xInput, OIConstants.DEADBAND);
        yInput = MathUtil.applyDeadband(yInput, OIConstants.DEADBAND);
        rotInput = MathUtil.applyDeadband(rotInput, OIConstants.DEADBAND);
        
        xInput = Math.copySign(xInput * xInput, xInput);
        yInput = Math.copySign(yInput * yInput, yInput);
        rotInput = Math.copySign(rotInput * rotInput, rotInput);
        
        double xSpeed = xInput * SwerveConstants.MAX_SPEED_MPS * OIConstants.SPEED_MULTIPLIER;
        double ySpeed = yInput * SwerveConstants.MAX_SPEED_MPS * OIConstants.SPEED_MULTIPLIER;
        double rotSpeed = rotInput * SwerveConstants.MAX_ANGULAR_SPEED * OIConstants.SPEED_MULTIPLIER;
        
        swerveDrive.drive(
            new Translation2d(xSpeed, ySpeed),
            rotSpeed
        );
    }
    
    @Override
    public void end(boolean interrupted) {
        swerveDrive.drive(new Translation2d(0, 0), 0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}