package com.example.myapplication.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class SaveimageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_image);

        final Button save = (Button)findViewById(R.id.sbtni);
        Button input = (Button)findViewById(R.id.inputimage);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs:// ... ");

//생성된 FirebaseStorage를 참조하는 storage 생성
        StorageReference storageRef = storage.getReference();

//Storage 내부의 images 폴더 안의 image.jpg 파일명을 가리키는 참조 생성
        final StorageReference pathReference = storageRef.child("images/image.jpg");


        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    File path = new File("Folder path");
                     final File file = new File(path,"File name");
                    try {
                        if (!path.exists()) {
                            //저장할 폴더가 없으면 생성
                            path.mkdirs();
                        }
                        file.createNewFile();

                        //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                        final FileDownloadTask fileDownloadTask = pathReference.getFile(file);
                        fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                //다운로드 성공 후 할 일
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                //다운로드 실패 후 할 일
                            }
                        }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            //진행상태 표시
                            public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
