package com.ajaybhatia.dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static TextView tv_cpu;
    static TextView tv_you;
    static TextView tv_status;

    static Button btn_even;
    static Button btn_roll;
    static Button btn_odd;

    static ImageView iv_dice1;
    static ImageView iv_dice2;

    String currentPick = "";

    int cpuCount = 0;
    int youCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_cpu = (TextView) findViewById(R.id.tv_cpu);
        tv_you = (TextView) findViewById(R.id.tv_you);
        tv_status = (TextView) findViewById(R.id.tv_status);

        btn_even = (Button) findViewById(R.id.btn_even);
        btn_roll = (Button) findViewById(R.id.btn_roll);
        btn_odd = (Button) findViewById(R.id.btn_odd);

        iv_dice1 = (ImageView) findViewById(R.id.iv_dice1);
        iv_dice2 = (ImageView) findViewById(R.id.iv_dice2);

        btn_even.setVisibility(View.VISIBLE);
        btn_roll.setVisibility(View.INVISIBLE);
        btn_odd.setVisibility(View.VISIBLE);

        btn_even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPick = "even";
                tv_status.setText(currentPick);
                btn_even.setVisibility(View.INVISIBLE);
                btn_roll.setVisibility(View.VISIBLE);
                btn_odd.setVisibility(View.INVISIBLE);
            }
        });

        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int dice1Roll = random.nextInt(6) + 1;
                int dice2Roll = random.nextInt(6) + 1;

                setDiceImages(dice1Roll, dice2Roll);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animate);
                iv_dice1.startAnimation(animation);
                iv_dice2.startAnimation(animation);

                int freeHand = (dice1Roll + dice2Roll) % 2;

                Log.i("GAMEAPP DICE 1", String.valueOf(dice1Roll));
                Log.i("GAMEAPP DICE 2", String.valueOf(dice2Roll));
                Log.i("GAMEAPP FREE HAND", String.valueOf(freeHand));

                if (currentPick.equals("even"))
                    if (freeHand == 0)
                        youCount++;
                    else
                        cpuCount++;

                if (currentPick.equals("odd"))
                    if (freeHand == 1)
                        youCount++;
                    else
                        cpuCount++;

                tv_cpu.setText("CPU: " + cpuCount);
                tv_you.setText("YOU: " + youCount);

                btn_even.setVisibility(View.VISIBLE);
                btn_roll.setVisibility(View.INVISIBLE);
                btn_odd.setVisibility(View.VISIBLE);
            }
        });

        btn_odd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPick = "odd";
                tv_status.setText(currentPick);
                btn_even.setVisibility(View.INVISIBLE);
                btn_roll.setVisibility(View.VISIBLE);
                btn_odd.setVisibility(View.INVISIBLE);
            }
        });
    }

    private static void chooseDiceImage(int diceRoll, ImageView view) {
        switch (diceRoll) {
            case 1:
                view.setImageResource(R.drawable.die_face_1);
                break;

            case 2:
                view.setImageResource(R.drawable.die_face_2);
                break;

            case 3:
                view.setImageResource(R.drawable.die_face_3);
                break;

            case 4:
                view.setImageResource(R.drawable.die_face_4);
                break;

            case 5:
                view.setImageResource(R.drawable.die_face_5);
                break;

            case 6:
                view.setImageResource(R.drawable.die_face_6);
                break;
        }
    }

    private static void setDiceImages(int dice1Roll, int dice2Roll) {
        chooseDiceImage(dice1Roll, iv_dice1);
        chooseDiceImage(dice2Roll, iv_dice2);
    }
}
