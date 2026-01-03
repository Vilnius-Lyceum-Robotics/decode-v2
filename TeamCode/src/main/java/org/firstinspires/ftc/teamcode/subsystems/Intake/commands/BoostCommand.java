package org.firstinspires.ftc.teamcode.subsystems.Intake.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeConfiguration;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;

public class BoostCommand extends SequentialCommandGroup {
    public BoostCommand(Intake intake) {
        addCommands(
//                new InstantCommand(() -> intake.setLift(IntakeConfiguration.LIFT_UP_POS)),
                new WaitCommand(1000)
//                new InstantCommand(() -> intake.setLift(IntakeConfiguration.LIFT_DOWN_POS))
        );
        addRequirements(intake);
    }
}
