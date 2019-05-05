package com.example.myapplication.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText ev_id;
    EditText ev_pwd;
    ImageButton btn_regist;
    ImageButton btn_login;

    boolean result_login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ev_id = (EditText)findViewById(R.id.ev_id);
        ev_pwd = (EditText)findViewById(R.id.ev_pwd);
        btn_login = (ImageButton)findViewById(R.id.btn_login);
        btn_regist= (ImageButton)findViewById(R.id.btn_regist);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ev_id.getText().toString().equals("admin")){
                    if (ev_pwd.getText().toString().equals("1234")){
                        Intent intent = new Intent(getApplicationContext(),regist_bank.class);
                        startActivity(intent);
                    }
                }else {
                    GetData task = new GetData();
                    task.execute("http://35.189.148.163/login_.php");
                }
            }
        });

    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog = ProgressDialog.show(LoginActivity.this, "Please Wait", null, true,true);

        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d("", "response -" +result);

            if(result==null){

                // mTextViewResult.setText(errorString);
            }
            else{

                if(result.equals("1")) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    savePreferences("id", ev_id.getText().toString());
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this,"로그인에 실패했습니다,",Toast.LENGTH_SHORT).show();
                }

                //mJsonString = result;
                //showResult();
            }
        }

        @Override
        protected String doInBackground(String... params){

            String id = ev_id.getText().toString();
            String pwd = ev_pwd.getText().toString();
            String serverURL = params[0];
            serverURL+= "?id="+id+"&&pwd="+pwd;
            try{

                URL url=new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode= httpURLConnection.getResponseCode();
                Log.d("", "response code - " +responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode==httpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();

                }
                else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();
            }catch (Exception e) {

                Log.d("", "InsertData: Error",e);
                errorString=e.toString();

                return null;
            }
        }
    }

    // 값 저장하기
    private void savePreferences(String key, String value){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }


}
