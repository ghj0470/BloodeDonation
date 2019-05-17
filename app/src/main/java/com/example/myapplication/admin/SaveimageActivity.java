package com.example.myapplication.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveimageActivity extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_iMAGE = 2;
    private Uri mImageCaptureUri;
    private ImageView iview;
    private Button input = (Button)findViewById(R.id.inputimage);
    private DB_Manager dmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_image);

        final Button save = (Button)findViewById(R.id.sbtni);



        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlbumAction();
            }
        });

    }
    public void AlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);
    }

    public void onActivityResult(int request, int result,Intent data){
        if(result!=RESULT_OK)
            return;
        switch(request){
            case PICK_FROM_CAMERA:{
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                    // CROP할 이미지를 200*200 크기로 저장
                intent.putExtra("outputX", 200); // CROP한 이미지의 x축 크기
                intent.putExtra("outputY", 200); // CROP한 이미지의 y축 크기
                intent.putExtra("aspectX", 1); // CROP 박스의 X축 비율
                intent.putExtra("aspectY", 1); // CROP 박스의 Y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_iMAGE);
                break;

            }
            case PICK_FROM_ALBUM:{
                mImageCaptureUri=data.getData();
                Log.o("SmartWheel",mImageCaptureUri.getPath().toString());
            }
            case CROP_FROM_iMAGE: {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                if(resultCode != RESULT_OK) {
                    return;
                }

                final Bundle extras = data.getExtras();

                // CROP된 이미지를 저장하기 위한 FILE 경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/SmartWheel/"+System.currentTimeMillis()+".jpg";

                if(extras != null) {
                Bitmap photo = extras.getParcelable("data"); // CROP된 BITMAP
                iv_UserPhoto.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                storeCropImage(photo, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                absoultePath = filePath;
                break;
                }
                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists()) {
                f.delete();
                }
            }

        }
    }

    @Override
    public void onClick(View v) {
        iview = v.getId();
        if(v.getId() == R.id.btn_signupfinish) {
            /** SharedPreference 환경 변수 사용 **/
            SharedPreferences prefs = getSharedPreferences("login", 0);
            /** prefs.getString() return값이 null이라면 2번째 함수를 대입한다. **/
            String login = prefs.getString("USER_LOGIN", "LOGOUT");
            String facebook_login = prefs.getString("FACEBOOK_LOGIN", "LOGOUT");
            String user_id = prefs.getString("USER_ID", "");
            String user_name = prefs.getString("USER_NAME", "");
            String user_password = prefs.getString("USER_PASSWORD", "");
            String user_phone = prefs.getString("USER_PHONE", "");
            String user_email = prefs.getString("USER_EMAIL", "");
            dmanager.select(user_id, user_name, user_password, user_phone, user_email);
            dmanager.selectPhoto(user_name, mImageCaptureUri, absoultePath);

            Intent mainIntent = new Intent(SaveimageActivity.this, LoginActivity.class);
            SaveimageActivity.this.startActivity(mainIntent);
            SaveimageActivity.this.finish();
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.inputimage) {
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            doTakePhotoAction();
            }
            };
            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            doTakeAlbumAction();
            }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
            };

            new AlertDialog.Builder(this)
            .setTitle("업로드할 이미지 선택")
            .setPositiveButton("사진촬영", cameraListener)
            .setNeutralButton("앨범선택", albumListener)
            .setNegativeButton("취소", cancelListener)
            .show();
        }

    }

/*
   * Bitmap을 저장하는 부분
   */
    private void storeCropImage(Bitmap bitmap, String filePath) {
        // SmartWheel 폴더를 생성하여 이미지를 저장하는 방식이다.
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel";
        File directory_SmartWheel = new File(dirPath);

        if(!directory_SmartWheel.exists()) // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우에 속한다.)
            directory_SmartWheel.mkdir();

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try {

            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            // sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신한다.
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(copyFile)));

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


