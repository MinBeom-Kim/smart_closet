package com.example.smart_closet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDataUpdate extends AppCompatActivity {
    DatabaseHelper db;
    TextView email;
    EditText password, con_password, name;
    ImageView profile;
    Button pro_select, pro_re, update;
    SQLiteDatabase sqlDB;

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
        ArrayList<String> userData = (ArrayList<String>) intent.getSerializableExtra("userData");
        email.setText(userData.get(0));



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "회원 정보가 성공적으로 수정되었습니다", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}

