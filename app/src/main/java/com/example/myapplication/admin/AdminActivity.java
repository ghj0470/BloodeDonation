package com.example.myapplication.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button cbtn = (Button)findViewById(R.id.conbutton);
        Button rbtn = (Button)findViewById(R.id.rewardbutton);
        Button lobtn = (Button)findViewById(R.id.logoutbutton);
        Button dbtn = (Button)findViewById(R.id.deletebutton);

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this, SaveContentActivity.class);
                startActivity(intent);
            }
        });

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this, SaveimageActivity.class);
                startActivity(intent);
            }
        });

        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(AdminActivity.this,deleteActivity.class);
                startActivity(intent);
            }
        });
        lobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(AdminActivity.this,SaveContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
