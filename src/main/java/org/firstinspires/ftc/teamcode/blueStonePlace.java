package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="Blue Side Stone Delivery", group="Quarry")
public class blueStonePlace extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftfront = null;
    private DcMotor rightfront = null;
    private DcMotor leftrear = null;
    private DcMotor rightrear = null;
    private DcMotor elevator = null;
    private DcMotor wrist = null;
    private Servo intakeA = null;
    private Servo intakeB = null;
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
            sleep(time);}


    public void setElevator (double speed, int time) {
        leftfront.setPower(speed);
        rightrear.setPower(speed);
        leftrear.setPower(-speed);
        rightfront.setPower(-speed);
        sleep(time);
    }








      public void setWrist(double power, int time){
        wrist.setPower(power);
        sleep(time);
        wrist.setPower(0);
      }

      public void setIntake (int intaking){
        if (intaking == 1) {
            intakeA.setPosition(1);
            intakeB.setPosition(0);
        }
        else if (intaking == 0) {
            intakeA.setPosition(0);
            intakeB.setPosition(1);
        }

      }
      public void servof( int isdeployed){
        if (isdeployed == 1){
            foundationServo.setPosition(0.5);
        }
        else if (isdeployed == 0){
            foundationServo.setPosition(0);
        }
        sleep(500);
      }







    public void Path(){
        runtime.reset();
        power(-1,1000);
        power(0,500);
        setElevator(1,1000);
        setWrist(1, 750);
        setIntake(0);
        setElevator(0,500);
        setWrist(0,500);
        setElevator(-1,1000);
        sleep(500);
        setIntake(1);
        setElevator(0,250);
        setWrist(-1,1000);
        setWrist(0,250);
        power(1,500);
        power(0,250);
        strafe(-1, 5000);
        strafe(0,250);
        power(-1,1000);
        power(0,250);
        setElevator(1,1000);
        setElevator(0,250);
        setWrist(1,500);
        setWrist(0,250);
        setElevator(-1,1000);
        setElevator(0,250);
        setIntake(0);
        servof(1);
        power(1,1750);
        power(0,250);
        servof(0);
        strafe(1,1500);
        power(0,500);
        turn(-.5,1000);
        power(0,500);
        power(-.5,750);
        power(0,500);
        strafe(.5,1300);
        strafe(0,250);





    }
    @Override
    public void runOpMode() {
        leftfront  = hardwareMap.get(DcMotor.class, "left_front");
        rightfront = hardwareMap.get(DcMotor.class, "right_front");
        leftrear = hardwareMap.get(DcMotor.class, "left_rear");
        rightrear = hardwareMap.get(DcMotor.class, "right_rear");
        foundationServo = hardwareMap.get(Servo.class, "servof");
        elevator = hardwareMap.get(DcMotor.class, "motor");
        wrist = hardwareMap.get(DcMotor.class, "motor2");
        intakeA = hardwareMap.get(Servo.class,"servo1");
        intakeB = hardwareMap.get(Servo.class,"servo2");

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

        Path();
    }
}