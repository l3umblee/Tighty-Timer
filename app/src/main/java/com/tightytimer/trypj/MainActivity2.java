package com.tightytimer.trypj;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "Main_Activity2";

    private TextView tvCount, leftCount, rest_txt;
    private ImageView imageadd, imagerm, imagereset, imagerest;
    private int count=0, left_num=0, save = 0, rest_num = 0;
    private CountDownTimer resttimer;
    private long timego = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");
        leftCount = findViewById(R.id.lefttext);
        rest_txt = findViewById(R.id.time_text);
        rest_txt.setVisibility(View.INVISIBLE);
        imageadd = findViewById(R.id.btn_add);
        imagerm = findViewById(R.id.btn_minus);
        imagereset = findViewById(R.id.btn_reset);
        imagerest = findViewById(R.id.btn_timer);
        Intent intent = getIntent();

        int leftcnt = intent.getIntExtra("sets", 0);
        int resttime = intent.getIntExtra("rests", 0);

        left_num = leftcnt;
        save = leftcnt;
        rest_num = resttime + 1;
        resttimer = new CountDownTimer(rest_num*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timego = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                tvCount.setVisibility(View.VISIBLE);
                imageadd.setVisibility(View.VISIBLE);
                imagereset.setVisibility(View.VISIBLE);
                imagerm.setVisibility(View.VISIBLE);
                rest_txt.setVisibility(View.INVISIBLE);
            }
        };
        leftCount.setText(leftcnt+" set 남음");
        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btnAdd : "+v.getClass().getName());
                count++;
                left_num--;
                tvCount.setText(count+"");
                if(left_num >= 0)
                    leftCount.setText(left_num+" set 남음");
                else
                    leftCount.setText("한계 돌파!");
            }
        });

        imagerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    left_num++;
                    tvCount.setText(count + "");
                    if(left_num >= 0)
                        leftCount.setText(left_num+" set 남음");
                    else
                        leftCount.setText("한계 돌파!");
                }

            }
        });

        imagereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                tvCount.setText(count + "");
                left_num = save;
                leftCount.setText(left_num+" set 남음");
            }
        });

        imagerest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setVisibility(View.INVISIBLE);
                imageadd.setVisibility(View.INVISIBLE);
                imagereset.setVisibility(View.INVISIBLE);
                imagerm.setVisibility(View.INVISIBLE);
                rest_txt.setVisibility(View.VISIBLE);
                resttimer.start();
            }
        });
    }
    public void updateCountDownText() {
        int sec = (int) (timego/1000) % 60;
        String timeLeft = String.format(Locale.getDefault(), "%02d", sec);
        rest_txt.setText(timeLeft);
    }
}