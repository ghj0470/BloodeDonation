package com.example.myapplication.paperweight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.information.SecondTwoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_one);

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
                Toast.makeText(SecondActivity.this, ""+year+"/"+(month+1)+"/"
                        +dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });
        Button btn1 = (Button)findViewById(R.id.button2);
        Button btn2 = (Button)findViewById(R.id.button3);
        Button btn3 = (Button)findViewById(R.id.button4);
        Button btn4 = (Button)findViewById(R.id.button5);
        Spinner year = (Spinner)findViewById(R.id.year);
        Spinner month = (Spinner)findViewById(R.id.month);
        Spinner day = (Spinner)findViewById(R.id.day);
        Spinner type = (Spinner)findViewById(R.id.type);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              // Toast.makeText(this,"현재 화면입니다.",Toast.LENGTH_LONG).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this, SecondOneActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this, SecondTwoActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter Ayear = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        ArrayAdapter Amonth = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        ArrayAdapter Aday = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        ArrayAdapter Atype = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        year.setAdapter(Ayear);
        month.setAdapter(Amonth);
        day.setAdapter(Aday);
        type.setAdapter(Atype);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        TextView mTextdate = (TextView)findViewById(R.id.textdate);
        SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd");
        String gtime = dt.format(date);
        mTextdate.setText(gtime);
    }
}