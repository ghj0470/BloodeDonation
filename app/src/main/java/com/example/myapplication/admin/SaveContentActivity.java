package com.example.myapplication.admin;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import org.w3c.dom.Text;



public class SaveContentActivity extends AppCompatActivity {

    EditText con;
    EditText tit;
    Button rbtn;
    Button sbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_content);

        con = (EditText)findViewById(R.id.context);
        tit = (EditText)findViewById(R.id.titletext);
        rbtn = (Button)findViewById(R.id.refbtn);
        sbtn = (Button)findViewById(R.id.savebtn);


        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if(con.getText().toString().equals("")){
                        Toast.makeText(SaveContentActivity.this,"내용이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else if(tit.getText().toString().equals("")){
                        Toast.makeText(SaveContentActivity.this,"제목이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Dbname=delist.toString();
                        insertDatabase(Dbname);
                    }
            }
        });




    }

}
