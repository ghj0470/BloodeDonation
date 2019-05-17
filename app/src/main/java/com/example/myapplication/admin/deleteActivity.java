package com.example.myapplication.admin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class deleteActivity extends AppCompatActivity {
    List<String> data;
    String Dbname;
    String Tname;
    ArrayAdapter<String>  adapter;
    MyAsyncTask mTask;
    final ListView delist = (ListView)findViewById(R.id.dlist);


    static String query = "select * from state order by curdate desc " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_content);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,data);
        delist.setAdapter(adapter);
        delist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Button dbtn = (Button)findViewById(R.id.dbutton);
        data = new ArrayList<String>(0);


        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(deleteActivity.this,"삭제하시겠습니까?",Toast.LENGTH_LONG);
                if(){
                    Dbname= (String) delist.toString().charAt(data);
                    deleteDatabase(Dbname);

                }
                else{
                    finish();
                }
            }
        });

    }

    protected void onStart(){
        super.onStart();

        handler.sendEmptyMessage(0);
    }
    class AsybcTask extends AsyncTask<String,Void, ArrayList<String>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected ArrayList<String> doInBackground( String... params){


            ArrayList<String> list = new ArrayList<String>();



            ResultSet reset = null;
            Connection conn = null;



            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://DB서버주소;databaseName=DB이름","아이디","패스워드");
                Statement stmt = conn.createStatement();

                reset = stmt.executeQuery(query);



                while(reset.next()){

                    if ( isCancelled() ) break;

                    final String str = reset.getString(1)+" "+reset.getString(3)+" "+reset.getString(4);
                    list.add(str);


                }
                conn.close();
            }

            catch (Exception e)
            {
                Log.w("111Error connection", "" + e.getMessage());
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){

            adapter.clear();
            adapter.addAll(list);


            adapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 1000);

        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
        }
    }

    public Handler handler = new Handler(){
        public void handleMessage( Message msg){
            super.handleMessage(msg);

            mTask = new MyAsyncTask();

            mTask.executeOnExe.cutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }
    };
}
}
