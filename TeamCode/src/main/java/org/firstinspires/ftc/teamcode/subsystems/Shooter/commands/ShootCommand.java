package org.firstinspires.ftc.teamcode.subsystems.Shooter.commands;


import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterConfiguration;

public class ShootCommand extends SequentialCommandGroup {

    public ShootCommand(Intake intake, Shooter shooter){

        addCommands(
                new InstantCommand(() -> intake.setTransfer(1, true)),
                new WaitCommand(500),
                new InstantCommand(() -> shooter.setLift(ShooterConfiguration.LIFT_UP_POS)),
                new WaitCommand(500),
                new InstantCommand(() -> intake.setTransfer(1, false)),
                new InstantCommand(() -> shooter.setLift(ShooterConfiguration.LIFT_DOWN_POS))
        );
        addRequirements(intake, shooter);
    }
}
