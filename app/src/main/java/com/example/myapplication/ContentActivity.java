package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView tit = (TextView)findViewById(R.id.titlecon);
        TextView date = (TextView)findViewById(R.id.datecon);
        TextView ref = (TextView)findViewById(R.id.refcon);
        TextView con = (TextView)findViewById(R.id.concon);

        String title = (String) tit.getText();
        String day = (String) date.getText();
        String referencce = (String) ref.getText();
        String content= (String) con.getText();

        boolean isOpen = openDatabase();
        if(isOpen){
            Cursor cursor = executeRawQueryParam();
            startManagingCursor(cursor);

            String[] colunms = new String[]{"title","day","reference","content"};
            int[] to = new int[]{R.id.titlecon,R.id.datecon,R.id.refcon,R.id.concon};

            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,R.layout.activity_content,cursor,colunms,to);
        }

    }

    private Cursor executeRawQueryParam() {

        String SQL = "select"+"from"+"where";
        String[] args={"10"};
        Cursor c = db.rawQuery(SQL,args);

        return c;
    }
}
