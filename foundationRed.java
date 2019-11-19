package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name=" Red Side Foundation ", group="Foundation")
public class foundationRed extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor leftrear = null;
    private DcMotor rightrear = null;
    private Servo foundationServo = null;
    public int restPeriod =400;
    //18.6 in = 40 rotations




    public void power(double speed, int distance){
        leftfront.setTargetPosition(distance);
        rightfront.setTargetPosition(-distance);
        leftrear.setTargetPosition(distance);
        rightrear.setTargetPosition(-distance);

        leftfront.setPower(-speed);
        rightfront.setPower(-speed);
        leftrear.setPower(-speed);
        rightrear.setPower(-speed);
        sleep(restPeriod);


    }
    public void turn (double speed, int distance) {
        leftfront.setTargetPosition(distance);
        rightfront.setTargetPosition(distance);
        leftrear.setTargetPosition(distance);
        rightrear.setTargetPosition(distance);

        leftfront.setPower(speed);
        rightfront.setPower(speed);
        leftrear.setPower(speed);
        rightrear.setPower(speed);
        sleep(restPeriod);
    }

    public void strafe(double speed, int distance) {
        leftfront.setTargetPosition(distance);
        rightfront.setTargetPosition(-distance);
        leftrear.setTargetPosition(-distance);
        rightrear.setTargetPosition(distance);

        leftfront.setPower(speed);
        rightfront.setPower(speed);
        leftrear.setPower(speed*.70);
        rightrear.setPower(speed*.70);
        sleep(restPeriod);
    }







    public void Path(){
        runtime.reset();
        power(1,60);

        foundationServo.setPosition(.5);
        sleep(500);

        power(-.5,0);

        foundationServo.setPosition(0);
        sleep(500);

        strafe(-1,100);

        power(1,40);

        strafe(-.5,60);


    }
    @Override
    public void runOpMode() {
        leftfront  = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftrear = hardwareMap.get(DcMotor.class, "left_rear");
        rightrear = hardwareMap.get(DcMotor.class, "right_rear");
        foundationServo = hardwareMap.get(Servo.class, "servof");

        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightrear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightrear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Path();

        leftfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftrear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightrear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}