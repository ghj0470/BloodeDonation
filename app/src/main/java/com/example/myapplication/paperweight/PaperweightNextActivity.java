package com.example.myapplication.paperweight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;

public class PaperweightNextActivity extends AppCompatActivity {

    Button button_medic;
    Button button_disease;
    Button button_malaria;
    Button button_Jakob;
    Button button_OK;

    Spinner spinner_medic;
    Spinner spinner_disease;
    Spinner spinner_malaria;
    Spinner spinner_Jakob;

    ImageView imageView_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperweight_next);

        //전자문진 검진 결과 출력
        button_OK = (Button) findViewById(R.id.button_OK);

        spinner_medic = (Spinner) findViewById(R.id.spinner_medic);
        spinner_disease = (Spinner) findViewById(R.id.spinner_disease);
        spinner_malaria = (Spinner) findViewById(R.id.spinner_malaria);
        spinner_Jakob = (Spinner) findViewById(R.id.spinner_Jakob);

        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner_medic.equals("해당없음")&&spinner_disease.equals("해당없음")&&spinner_malaria.equals("해당없음")&&spinner_Jakob.equals("해당없음")){
                    Toast.makeText(PaperweightNextActivity.this,"헌혈가능 대상입니다.",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(PaperweightNextActivity.this,"헌혈불가 대상입니다.",Toast.LENGTH_LONG).show();
                }

            }
        });


        //상태관련 이미지 출력
        button_medic = (Button) findViewById(R.id.button_medic);
        button_disease = (Button) findViewById(R.id.button_disease);
        button_malaria = (Button) findViewById(R.id.button_malaria);
        button_Jakob = (Button) findViewById(R.id.button_Jakob);

        imageView_show = (ImageView) findViewById(R.id.imageView_show);

        button_medic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_show.setImageResource(R.drawable.medic);
            }
        });

        button_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_show.setImageResource(R.drawable.medic);
            }
        });

        button_malaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_show.setImageResource(R.drawable.malaria);
            }
        });

        button_Jakob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_show.setImageResource(R.drawable.jakob);
            }
        });
    }
}
