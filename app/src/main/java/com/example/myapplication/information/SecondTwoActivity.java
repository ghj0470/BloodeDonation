package com.example.myapplication.information;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.paperweight.SecondActivity;
import com.example.myapplication.paperweight.SecondOneActivity;


public class SecondTwoActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sec_third);


            Button btn1 = (Button)findViewById(R.id.button6);
            Button btn2 = (Button)findViewById(R.id.button7);
            Button btn3 = (Button)findViewById(R.id.button8);
            Button btn4 = (Button)findViewById(R.id.button9);
            Button btn5 = (Button)findViewById(R.id.button10);

            EditText birth = (EditText)findViewById(R.id.birth);
            EditText kg = (EditText)findViewById(R.id.kg);
            EditText bmax = (EditText)findViewById(R.id.maxbp);
            EditText bmin = (EditText)findViewById(R.id.minbp);
            EditText temp = (EditText)findViewById(R.id.btemp);
            EditText pulse = (EditText)findViewById(R.id.bloodpulse);



            btn1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
            btn2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SecondTwoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SecondTwoActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            });
            btn4.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SecondTwoActivity.this, SecondOneActivity.class);
                    startActivity(intent);
                }
            });
            btn5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Toast.makeText(SecondTwoActivity.this,"현재 화면입니다.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

