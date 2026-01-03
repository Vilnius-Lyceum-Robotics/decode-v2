package org.firstinspires.ftc.teamcode.helpers.utils.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.helpers.subsystems.VLRSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Chassis.Chassis;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Intake.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.commands.ShootCommand;

@TeleOp(name = "Shooter test", group = "Tests")
public class Test extends CommandOpMode {
    private Shooter shooter;
    private Intake intake;
    private Chassis chassis;
    private GamepadEx firstDriver;

    private ShootCommand shootCommand;
    private IntakeCommand intakeCommand;

    @Override
    public void initialize() {
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        VLRSubsystem.requireSubsystems(Shooter.class, Intake.class, Chassis.class);
        VLRSubsystem.initializeAll(hardwareMap);

        shooter = VLRSubsystem.getInstance(Shooter.class);
        intake = VLRSubsystem.getInstance(Intake.class);
        chassis = VLRSubsystem.getInstance(Chassis.class);

        firstDriver = new GamepadEx(gamepad1);

        //shoot command
        shootCommand = new ShootCommand(intake, shooter);
        //intake command
        intakeCommand = new IntakeCommand(intake);

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(intakeCommand);
        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(shootCommand);

        //hood testing
        firstDriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> shooter.hoodUp());
        firstDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> shooter.hoodDown());

        //shooter testing
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> shooter.stopShooter());
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> shooter.liftUp());
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> shooter.liftDown());

        firstDriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> shooter.shooterPreset(1));
        firstDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> shooter.shooterPreset(2));
        firstDriver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> shooter.shooterPreset(3));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        chassis.drive(
                firstDriver.getLeftX(),
                firstDriver.getLeftY(),
                firstDriver.getRightX()
        );

        //Telemetry here
        shooter.telemetry(this.telemetry);
        telemetry.update();
    }
}