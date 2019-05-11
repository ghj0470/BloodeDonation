package com.example.myapplication.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;

public class deleteActivity extends AppCompatActivity {

    String Dbname;
    String Tname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_content);
        final ListView delist = (ListView)findViewById(R.id.dlist);
        Button dbtn = (Button)findViewById(R.id.dbutton);

        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(deleteActivity.this,"삭제하시겠습니까?",Toast.LENGTH_LONG);
                if(){
                    Dbname=delist.toString();
                    deleteDatabase(Dbname);

                }
                else{
                    finish();
                }
            }
        });

    }
}
