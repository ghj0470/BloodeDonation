package com.example.myapplication.paperweight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.myapplication.R;

public class PaperweightActivity extends AppCompatActivity {

    Button btn_paperNext;
    Button btn_yes;
    Button btn_no;

    Spinner spinner_today;
    Spinner spinner_day;
    Spinner spinner_week;
    Spinner spinner_month;
    Spinner spinner_6month;
    Spinner spinner_year;
    Spinner spinner_else;

    //ImageView imageView;

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //합치면 출력 레이아웃 바꿔라아아아ㅏㅏㅏㅏ
        setContentView(R.layout.activity_paperweight);

        //배제대상 예/아니오 선택시 실행
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_no = (Button) findViewById(R.id.btn_no);

        //imageView = (ImageView) findViewById(R.id.imageView);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout3);

        //이미지 비가시화
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView.setVisibility(View.INVISIBLE);
                tableLayout.setVisibility(View.INVISIBLE);
            }
        });

        //앱 종료
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //몸상태 점검후 다음
        btn_paperNext = (Button) findViewById(R.id.btn_paperNext);

        spinner_today = (Spinner) findViewById(R.id.spinner_today);
        spinner_day = (Spinner) findViewById(R.id.spinner_day);
        spinner_week = (Spinner) findViewById(R.id.spinner_week);
        spinner_month = (Spinner) findViewById(R.id.spinner_month);
        spinner_6month = (Spinner) findViewById(R.id.spinner_6month);
        spinner_year = (Spinner) findViewById(R.id.spinner_year);
        spinner_else = (Spinner) findViewById(R.id.spinner_else);

        btn_paperNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner_today.equals("해당없음")&&spinner_day.equals("해당없음")&&spinner_week.equals("해당없음")&&spinner_month.equals("해당없음")&&spinner_6month.equals("해당없음")&&spinner_year.equals("해당없음")&&spinner_else.equals("해당없음")){
                    setContentView(R.layout.activity_paperweight_next);
                }
                else {
                    Toast.makeText(PaperweightActivity.this,"헌혈불가 대상입니다.",Toast.LENGTH_LONG).show();
                }
            }
        });

        //0
    }
}
