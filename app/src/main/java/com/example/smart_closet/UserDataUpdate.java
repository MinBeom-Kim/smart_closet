package com.example.smart_closet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
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
    EditText edpassword, edcon_password, edname;
    ImageView profile;
    Button pro_select, pro_re, update;
    SQLiteDatabase sqlDB;
    Bitmap img;
    String mail, pw, name;
    String img_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update);

        db = new DatabaseHelper(this);

        email = (TextView) findViewById(R.id.update_email);
        edpassword = (EditText) findViewById(R.id.edit_password);
        edcon_password = (EditText) findViewById(R.id.update_con_password);
        edname = (EditText) findViewById(R.id.edit_name);
        profile = (ImageView) findViewById(R.id.user_profile);
        pro_select = (Button) findViewById(R.id.user_profile_select);
        pro_re = (Button) findViewById(R.id.user_profile_re);
        update = (Button) findViewById(R.id.update);

        final Intent intent = getIntent();
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
                Boolean update = db.user_pro_update(mail, img_pro);
                if (update == true) {
                    Toast.makeText(getApplicationContext(), img_pro, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "프로필이 성공적으로 등록되었습니다", Toast.LENGTH_SHORT).show();
                }
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
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            img_pro = uri.toString();
            profile.setImageURI(uri);
        }
    }
}
