package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="Red Side", group="Foundation")
public class foundationRed extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor leftrear = null;
    private DcMotor rightrear = null;
    private Servo foundationServo = null;



    public void power(double speed, int length){

        leftfront.setPower(speed);
        rightfront.setPower(speed);
        leftrear.setPower(speed);
        rightrear.setPower(speed);
        sleep(length);

    }
    public void turn(double speed, int length){

        leftfront.setPower(speed);
        rightfront.setPower(-speed);
        leftrear.setPower(speed);
        rightrear.setPower(-speed);
        sleep(length);    }

    public void strafe (double speed, int time)   {
        leftfront.setPower(speed);
        rightrear.setPower(speed*.5);
        leftrear.setPower(-speed*.5);
        rightfront.setPower(-speed);
        sleep(time);


    }






    public void Path(){
        runtime.reset();
        power(-1,1200);
        power(0,500);
        foundationServo.setPosition(.5);
        sleep(500);
        power(.5,3500);
        power(0,500);
        foundationServo.setPosition(0);
        sleep(1000);
        strafe(-1,1500);
        power(0,500);
        turn(.5,1000);
        power(0,500);
        power(-.5,750);
        power(0,500);
        strafe(-.5,1300);


    }
    @Override
    public void runOpMode() {
        leftfront  = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftrear = hardwareMap.get(DcMotor.class, "left_rear");
        rightrear = hardwareMap.get(DcMotor.class, "right_rear");
        foundationServo = hardwareMap.get(Servo.class, "servof");

        leftfront.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        leftrear.setDirection(DcMotor.Direction.FORWARD);
        rightrear.setDirection(DcMotor.Direction.REVERSE);

        Path();
    }
}