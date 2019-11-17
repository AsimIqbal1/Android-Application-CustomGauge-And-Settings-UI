package com.example.bluetoothappui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainActivity extends AppCompatActivity {


    CustomGauge cg1;
    ImageView btnInc, btnDec;
    TextView tv_temp;
    int initial_temp_val = 24;
    Button btnManual, btnAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cg1 = findViewById(R.id.gauge1);
        btnInc = findViewById(R.id.btnIncrease);
        btnDec = findViewById(R.id.btnDecrease);

        btnManual = findViewById(R.id.btn_manual);
        btnAuto = findViewById(R.id.btn_auto);

        tv_temp = findViewById(R.id.tv_ac_temp);
        tv_temp.setText(initial_temp_val+"");

        cg1.setPointSize(0);
        cg1.setSweepAngle(270);

        cg1.setPointStartColor(Color.parseColor("#00FF2B"));
        cg1.setPointEndColor(Color.parseColor("#FF0000"));
        cg1.setPointSize((initial_temp_val-15) * 18);

        cg1.setVisibility(View.INVISIBLE);
        cg1.setVisibility(View.VISIBLE);

        btnInc.setClickable(true);
        btnDec.setClickable(true);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c_temp_val = Integer.parseInt(tv_temp.getText().toString().trim());
                c_temp_val++;
                if(c_temp_val<=30){

                    tv_temp.setText(c_temp_val+"");

                    int x = cg1.getPointSize() + 18;
                    cg1.setPointSize(x);

                    cg1.setPointStartColor(Color.parseColor("#00FF2B"));
                    cg1.setPointEndColor(Color.parseColor("#FF0000"));

                    cg1.setVisibility(View.INVISIBLE);
                    cg1.setVisibility(View.VISIBLE);
                }
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c_temp_val = Integer.parseInt(tv_temp.getText().toString().trim());
                c_temp_val--;
                if(c_temp_val>=15) {
                    tv_temp.setText(c_temp_val +"");

                    int x = cg1.getPointSize() - 18;
                    cg1.setPointSize(x);

                    cg1.setPointStartColor(Color.parseColor("#00FF2B"));
                    cg1.setPointEndColor(Color.parseColor("#FF0000"));

//                    if(c_temp_val>=15 && c_temp_val<=19) {
//                        cg1.setPointStartColor(Color.parseColor("#00FF2B"));
//                    }else if(c_temp_val>=20 && c_temp_val<=23) {
//                        cg1.setPointStartColor(Color.parseColor("#B4FF5C"));
//                    }else if(c_temp_val>=24 && c_temp_val<=27) {
//                        cg1.setPointStartColor(Color.parseColor("#FFF05C"));
//                    }else if(c_temp_val>=28 && c_temp_val<=30) {
//                        cg1.setPointStartColor(Color.parseColor("#FF1717"));
//                    }

                    cg1.setVisibility(View.INVISIBLE);
                    cg1.setVisibility(View.VISIBLE);
                }
            }
        });

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnManual.setBackgroundColor(Color.parseColor("#ECD866"));
                btnAuto.setBackgroundColor(Color.parseColor("#ECBB10"));
            }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAuto.setBackgroundColor(Color.parseColor("#ECD866"));
                btnManual.setBackgroundColor(Color.parseColor("#ECBB10"));
            }
        });




//        for (int i=65535; i<=16711680;i += 1108740) {
//            String hexValue = "#" + Integer.toHexString(i);
//            cg1.setPointStartColor(Color.parseColor(hexValue));
//        }
        cg1.setPointEndColor(Color.parseColor("#E49797"));

//        startActivity(new Intent(this, SettingActivity.class));
    }
}
