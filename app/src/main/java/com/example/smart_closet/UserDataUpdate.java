package com.example.smart_closet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class UserDataUpdate extends AppCompatActivity {
    DatabaseHelper db;
    TextView email;
    EditText password, con_password, name;
    ImageView profile;
    Button pro_select, pro_re, update;
    SQLiteDatabase sqlDB;
    Bitmap img;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update);

        db = new DatabaseHelper(this);

        email = (TextView) findViewById(R.id.update_email);
        password = (EditText) findViewById(R.id.edit_password);
        con_password = (EditText) findViewById(R.id.update_con_password);
        name = (EditText) findViewById(R.id.edit_name);
        profile = (ImageView) findViewById(R.id.user_profile);
        pro_select = (Button) findViewById(R.id.user_profile_select);
        pro_re = (Button) findViewById(R.id.user_profile_re);
        update = (Button) findViewById(R.id.update);

        Intent intent = getIntent();
        final ArrayList<String> userData = (ArrayList<String>) intent.getSerializableExtra("userData");
        mail = userData.get(0);
        email.setText(userData.get(0));


        pro_select.setOnClickListener(new View.OnClickListener() {

            Intent intent = null;

            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 1);
                startActivity(intent);
            }
        });

        pro_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "프로필이 성공적으로 등록되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "회원 정보가 성공적으로 수정되었습니다", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Make sure the request was successful
            try {
                // 선택한 이미지에서 비트맵 생성
                InputStream in = getContentResolver().openInputStream(data.getData());
                img = BitmapFactory.decodeStream(in);
                in.close();
                profile.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

