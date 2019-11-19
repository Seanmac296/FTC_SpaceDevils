package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Competition Tele-Op", group = "Competition")
public class competition3TeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor leftrear = null;
    private DcMotor rightrear = null;
    private DcMotor elevator = null;
    private DcMotor wrist = null;
    private Servo intakeA = null;
    private Servo intakeB = null;
    private Servo foundation = null;

    public void driveMethod() {
        double leftfPower;
        double rightfPower;
        double leftrPower;
        double rightrPower;

        double drive = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double slow = (1 - gamepad1.right_trigger);
        leftfPower = Range.clip((drive + turn + strafe) / slow, -1.0, 1.0);
        rightfPower = Range.clip((drive - turn - strafe) / slow, -1.0, 1.0);
        leftrPower = Range.clip((drive + turn - strafe) / slow, -1.0, 1.0);
        rightrPower = Range.clip((drive - turn + strafe) / slow, -1.0, 1.0);

        leftfront.setPower(leftfPower);
        rightfront.setPower(rightfPower);
        leftrear.setPower(leftrPower);
        rightrear.setPower(rightrPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors: ", "LF:(%.2f), RF:(%.2f), LR:(%.2f), RR:(%.2f)", leftfront.getPower(), rightfront.getPower(), leftrear.getPower(), rightrear.getPower());
    }

    public void elevatorMethod() {
        boolean elevup = gamepad2.right_bumper;
        boolean elevdown = gamepad2.left_bumper;
        double delevu;
        double delevd;
        double delev;

        if (elevup) {
            delevu = 1;
        } else {
            delevu = 0;
        }
        if (elevdown) {
            delevd = 1;
        } else {
            delevd = 0;
        }
        delev = delevu - delevd;
        elevator.setPower(delev);
    }

    public void wristMethod() {
        double wristPower = (gamepad2.right_stick_y / -4);
        wrist.setPower(wristPower);
    }

    public void intakeMethod() {




        if (gamepad2.a ) {
            intakeA.setPosition(1);
            intakeB.setPosition(0);



        }
        if (gamepad2.b) {

            intakeA.setPosition(0);
            intakeB.setPosition(1);

        }


    }


    public void foundationMethod() {
        if (gamepad2.dpad_down) {
            foundation.setPosition(.5);
        }
        if (gamepad2.dpad_up) {
            foundation.setPosition(0);
        }

    }


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftfront = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftrear = hardwareMap.get(DcMotor.class, "left_rear");
        rightrear = hardwareMap.get(DcMotor.class, "right_rear");
        elevator = hardwareMap.get(DcMotor.class, "motor");
        wrist = hardwareMap.get(DcMotor.class, "motor2");
        intakeA = hardwareMap.get(Servo.class,"servo1");
        intakeB = hardwareMap.get(Servo.class,"servo2");
        foundation = hardwareMap.get(Servo.class, "servof");

        leftfront.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        leftrear.setDirection(DcMotor.Direction.FORWARD);
        rightrear.setDirection(DcMotor.Direction.REVERSE);
        elevator.setDirection(DcMotorSimple.Direction.FORWARD);
        wrist.setDirection(DcMotorSimple.Direction.FORWARD);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightrear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        driveMethod();
        elevatorMethod();
        wristMethod();
        intakeMethod();
        foundationMethod();
    }

    @Override
    public void stop() {
    }

}
